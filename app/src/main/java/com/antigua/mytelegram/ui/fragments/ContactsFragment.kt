package com.antigua.mytelegram.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.antigua.mytelegram.R
import com.antigua.mytelegram.models.CommonModel
import com.antigua.mytelegram.ui.fragments.single_chat.SingleChatFragment
import com.antigua.mytelegram.utilits.*
import com.antigua.mytelegram.utilits.AppConstants.APP_ACTIVITY
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseReference
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.contact_item.view.*
import kotlinx.android.synthetic.main.fragment_contacts.*

class ContactsFragment : BaseFragment(R.layout.fragment_contacts) {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: FirebaseRecyclerAdapter<CommonModel,ContactsHolder>
    private lateinit var mRefContacts: DatabaseReference
    private lateinit var mRefUsers: DatabaseReference
    private lateinit var mRefUserListener: AppValueEventListener
    private  var mapListeners = hashMapOf<DatabaseReference,AppValueEventListener>()

    override fun onResume() {
        super.onResume()
        APP_ACTIVITY.title = "Контакты"
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView = contacts_recycle_view
        mRefContacts = REF_DATABASE_ROOT.child(NODE_PHONES_CONTACTS).child(CURRENT_UID)

        /* Настройка для адаптера , где указываем какие данные  и откуда получать */
        val options = FirebaseRecyclerOptions.Builder<CommonModel>()
            .setQuery(mRefContacts,CommonModel::class.java)
            .build()

        /* Адаптер принимает данные , отображает в холдере */
        mAdapter = object : FirebaseRecyclerAdapter<CommonModel,ContactsHolder> (options){

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsHolder {
                 /* Запускается тогда когда адаптер получает доступ к ViewGroup */
                  val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item,parent,false)
                return  ContactsHolder(view)
            }

            override fun onBindViewHolder(
                holder: ContactsHolder,
                position: Int,
                model: CommonModel
            ) {
               mRefUsers = REF_DATABASE_ROOT.child(NODE_USERS).child(model.id)
                mRefUserListener = AppValueEventListener {
                    val contact = it.getCommonModel()
                    if (contact.fullname.isEmpty()){
                        holder.name.text  = model.fullname
                    } else {
                        holder.name.text = contact.fullname
                    }
                    holder.status.text = contact.state
                    holder.photo.downloadAndSetImage(contact.photoUrl)
                    holder.itemView.setOnClickListener { replaceFragment(SingleChatFragment(model)) }
                }

               mRefUsers.addValueEventListener(mRefUserListener)
                mapListeners[mRefUsers] = mRefUserListener
            }
        }
        mRecyclerView.adapter = mAdapter
        mAdapter.startListening()
    }

    class ContactsHolder(view: View) :RecyclerView.ViewHolder(view){
        val name: TextView = view.contact_fullname
        val status: TextView = view.contact_status
        val photo: CircleImageView = view.contact_photo
    }

    override fun onPause() {
        super.onPause()
        mAdapter.stopListening()
        mapListeners.forEach{
            it.key.removeEventListener(it.value)
        }
    }
}




