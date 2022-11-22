package com.antigua.mytelegram.ui.fragments.single_chat

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.AbsListView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.antigua.mytelegram.R
import com.antigua.mytelegram.models.CommonModel
import com.antigua.mytelegram.models.UserModel
import com.antigua.mytelegram.ui.fragments.BaseFragment
import com.antigua.mytelegram.utilits.*
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY
import com.google.firebase.database.DatabaseReference
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.fragment_single_chat.*
import kotlinx.android.synthetic.main.toolbar_info.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SingleChatFragment(private val contact: CommonModel) : BaseFragment(R.layout.fragment_single_chat) {

    private lateinit var mListenerInfoToolbar: AppValueEventListener
    private lateinit var mReceivingUser: UserModel
    private lateinit var mToolbarInfo: View
    private lateinit var mRefUser: DatabaseReference
    private lateinit var mRefMessages :DatabaseReference
    private lateinit var mAdapter: SingleChatAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mMessageListener: AppChildEventListener
    private var mCountMessages = 10
    private var mIsScrolling = false
    private var mSmoothScrollToPosition = true
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAppVoiceRecorder: AppVoiceRecorder


    override fun onResume() {
        super.onResume()
        initFields()
        initToolbar()
        initRecycleView()
    }

    override fun onDestroy() {
        super.onDestroy()
        mAppVoiceRecorder.releaseRecorder()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initFields() {
        mAppVoiceRecorder = AppVoiceRecorder()
        mSwipeRefreshLayout = chat_swipe_refresh
        mLayoutManager =  LinearLayoutManager(this.context)
        chat_input_message.addTextChangedListener(AppTextWatcher{
            val string = chat_input_message.text.toString()
            if(string.isEmpty()||string == "Запись"){
                chat_btn_send_message.visibility = View.GONE
                chat_btn_attach.visibility = View.VISIBLE
                chat_btn_voice.visibility = View.VISIBLE
            } else {
                chat_btn_send_message.visibility = View.VISIBLE
                chat_btn_attach.visibility = View.GONE
                chat_btn_voice.visibility = View.GONE
            }
        })
        chat_btn_attach.setOnClickListener { attachFile() }

        CoroutineScope(Dispatchers.IO).launch {
            chat_btn_voice.setOnTouchListener { v, event ->
                if(checkPermission(RECORD_AUDIO)){
                    if(event.action == MotionEvent.ACTION_DOWN){
                        //TODO record
                        chat_input_message.setText("Запись")
                        chat_btn_voice.setColorFilter(ContextCompat.getColor(APP_ACTIVITY,R.color.colorPrimary))
                        val messageKey = getMessageKey(contact.id)
                        mAppVoiceRecorder.startRecord(messageKey)
                    } else if(event.action == MotionEvent.ACTION_UP){
                        //TODO stop record
                        chat_input_message.setText("")
                        chat_btn_voice.colorFilter = null
                        mAppVoiceRecorder.stopRecord{ file,messageKey ->
                            uploadFileToStorage(Uri.fromFile(file),messageKey)
                        }
                    }
                }
                true
            }
        }
    }



    private fun attachFile() {
        CropImage.activity()
            .setAspectRatio(1,1)
            .setRequestedSize(600,600)
            .start(APP_ACTIVITY,this)
    }

    private fun initRecycleView() {
        mRecyclerView = chat_recycler_view
        mAdapter = SingleChatAdapter()
        mRefMessages = REF_DATABASE_ROOT
            .child(NODE_MESSAGES)
            .child(CURRENT_UID)
            .child(contact.id)
        mRecyclerView.adapter = mAdapter
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.isNestedScrollingEnabled =false
        mRecyclerView.layoutManager =  mLayoutManager
        mMessageListener = AppChildEventListener {
          val message  = it.getCommonModel()

           if (mSmoothScrollToPosition){
               mAdapter.addItemToBottom(message){
                   mRecyclerView.smoothScrollToPosition(mAdapter.itemCount)
               }
           } else {
               mAdapter.addItemToTop(message){
                   mSwipeRefreshLayout.isRefreshing = false
               }
           }
        }

        mRefMessages.limitToLast(mCountMessages).addChildEventListener(mMessageListener)


        mRecyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    mIsScrolling  = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if(mIsScrolling && dy<0 && mLayoutManager.findFirstVisibleItemPosition() <=3 ){
                    updateData()
                }
            }

        })
        mSwipeRefreshLayout.setOnRefreshListener { updateData() }
    }

    private fun updateData() {
        mSmoothScrollToPosition = false
        mIsScrolling = false
        mCountMessages +=10
        mRefMessages.removeEventListener(mMessageListener)
        mRefMessages.limitToLast(mCountMessages).addChildEventListener(mMessageListener)

    }

    private fun initToolbar() {
        mToolbarInfo = APP_ACTIVITY.mToolbar.toolbar_info
        mToolbarInfo.visibility = View.VISIBLE
        mListenerInfoToolbar = AppValueEventListener {
            mReceivingUser = it.getUserModel()
            initInfoToolbar()
        }
        mRefUser = REF_DATABASE_ROOT.child(NODE_USERS).child(contact.id)
        mRefUser.addValueEventListener(mListenerInfoToolbar)
        chat_btn_send_message.setOnClickListener {
            mSmoothScrollToPosition = true
            val message = chat_input_message.text.toString()
            if (message.isEmpty()) {
                showToast("Введите сообщение")
            } else {
                sendMessage(message, contact.id, TYPE_TEXT) {
                    chat_input_message.setText("")
                }
            }
        }
    }

    private fun initInfoToolbar() {
        if (mReceivingUser.fullname.isEmpty()){
            mToolbarInfo.toolbar_chat_fullname.text =  contact.fullname
        } else {
            mToolbarInfo.toolbar_chat_fullname.text =  mReceivingUser.fullname
        }
        mToolbarInfo.toolbar_chat_image.downloadAndSetImage(mReceivingUser.photoUrl)
        mToolbarInfo.toolbar_chat_status.text =  mReceivingUser.state
    }

    override fun onPause() {
        super.onPause()
        mToolbarInfo.visibility = View.GONE
        mRefUser.removeEventListener(mListenerInfoToolbar)
        mRefMessages.removeEventListener(mMessageListener)
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE
            && resultCode == Activity.RESULT_OK && data != null){
            Log.d("MyLog","CropImage -> RESULT_OK")
            val uri = CropImage.getActivityResult(data).uri
            val messageKey = getMessageKey(contact.id)

            val path = REF_STORAGE_ROOT
                .child(FOLDER_MESSAGE_IMAGE)
                .child(messageKey)
            Log.d("MyLog","path  -> $path")
            putImageToStorage(uri,path){
                getUrlFromStorage(path){
                    sendMessageAsImage(contact.id,it,messageKey)
                    mSmoothScrollToPosition = true
                }
            }
        }
    }




}





