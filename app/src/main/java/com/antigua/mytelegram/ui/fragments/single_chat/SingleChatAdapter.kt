package com.antigua.mytelegram.ui.fragments.single_chat

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.antigua.mytelegram.database.CURRENT_UID
import com.antigua.mytelegram.ui.fragments.message_recycler_view.view_holders.AppHolderFactory
import com.antigua.mytelegram.ui.fragments.message_recycler_view.view_holders.HolderImageMessage
import com.antigua.mytelegram.ui.fragments.message_recycler_view.view_holders.HolderTextMessage
import com.antigua.mytelegram.ui.fragments.message_recycler_view.views.MessageView
import com.antigua.mytelegram.utilits.asTime
import com.antigua.mytelegram.utilits.downloadAndSetImage

class SingleChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mListMessagesCache = mutableListOf<MessageView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return  AppHolderFactory.getHolder(parent,viewType)
    }

    override fun getItemViewType(position: Int): Int {
        return mListMessagesCache[position].getTypeView()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HolderImageMessage ->drawMessageImage(holder,position)
            is HolderTextMessage ->drawMessageText(holder,position)
            else -> {}
        }
    }

    private fun drawMessageImage(holder: HolderImageMessage, position: Int) {

        if(mListMessagesCache[position].from == CURRENT_UID){
            holder.blockReceivedImageMessage.visibility = View.GONE
            holder.blockUserImageMessage.visibility = View.VISIBLE
            holder.chatUserImage.downloadAndSetImage(mListMessagesCache[position].fileUrl)
            holder.chatUserImageMessageTime.text = mListMessagesCache[position].timeStamp.asTime()
        } else {
            holder.blockReceivedImageMessage.visibility = View.VISIBLE
            holder.blockUserImageMessage.visibility = View.GONE
            holder.chatReceivedImage.downloadAndSetImage(mListMessagesCache[position].fileUrl)
            holder.chatReceivedImageMessageTime.text = mListMessagesCache[position].timeStamp.asTime()
        }
    }

    private fun drawMessageText(holder: HolderTextMessage, position: Int) {

        if(mListMessagesCache[position].from == CURRENT_UID){
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCache[position].text
            holder.chatUserMessageTime.text =
                mListMessagesCache[position].timeStamp.asTime()
        } else {
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = mListMessagesCache[position].text
            holder.chatReceivedMessageTime.text =
                mListMessagesCache[position].timeStamp.asTime()
        }
    }

    override fun getItemCount(): Int = mListMessagesCache.size

   fun addItemToBottom(
       item: MessageView,
       onSuccess: ()->Unit
   ){
       if (!mListMessagesCache.contains(item)){
           mListMessagesCache.add(item)
           notifyItemInserted(mListMessagesCache.size)
       }
       onSuccess()
   }

    fun addItemToTop(
        item: MessageView,
        onSuccess: ()->Unit
    ){
        if (!mListMessagesCache.contains(item)){
            mListMessagesCache.add(item)
            mListMessagesCache.sortBy { it.timeStamp }
            notifyItemInserted(0)
        }
        onSuccess()
    }
}


