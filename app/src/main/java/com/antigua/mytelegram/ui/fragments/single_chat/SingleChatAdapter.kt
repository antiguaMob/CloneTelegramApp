package com.antigua.mytelegram.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.antigua.mytelegram.R
import com.antigua.mytelegram.models.CommonModel
import com.antigua.mytelegram.utilits.AppConstants.TYPE_MESSAGE_IMAGE
import com.antigua.mytelegram.utilits.AppConstants.TYPE_MESSAGE_TEXT
import com.antigua.mytelegram.database.CURRENT_UID
import com.antigua.mytelegram.utilits.asTime
import com.antigua.mytelegram.utilits.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCache = mutableListOf<CommonModel>()

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view){
        //Text
        val blockUserMessage : ConstraintLayout = view.block_user_message
        val chatUserMessage : TextView = view.chat_user_message
        val chatUserMessageTime : TextView = view.chat_user_massage_time
        val blockReceivedMessage : ConstraintLayout = view.block_received_message
        val chatReceivedMessage : TextView = view.chat_received_message
        val chatReceivedMessageTime : TextView = view.chat_received_massage_time

       //Image
       val blockReceivedImageMessage: ConstraintLayout = view.block_received_image_message
       val blockUserImageMessage: ConstraintLayout = view.block_user_image_message
       val chatUserImage: ImageView =  view.chat_user_image
       val chatReceivedImage: ImageView =  view.chat_received_image
       val chatUserImageMessageTime: TextView = view.chat_user_image_massage_time
       val chatReceivedImageMessageTime: TextView = view.chat_received_image_massage_time

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent,false)
        return  SingleChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
       when(mListMessagesCache[position].type){
           TYPE_MESSAGE_TEXT -> drawMessageText(holder,position)
           TYPE_MESSAGE_IMAGE -> drawMessageImage(holder,position)
       }
    }

    private fun drawMessageImage(holder: SingleChatHolder, position: Int) {
        holder.blockUserMessage.visibility = View.GONE
        holder.chatReceivedMessage.visibility = View.GONE

        if(mListMessagesCache[position].from == CURRENT_UID){
            holder.blockReceivedImageMessage.visibility = View.GONE
            holder.blockUserImageMessage.visibility = View.VISIBLE
            holder.chatUserImage.downloadAndSetImage(mListMessagesCache[position].fileUrl)
            holder.chatUserImageMessageTime.text = mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockReceivedImageMessage.visibility = View.VISIBLE
            holder.blockUserImageMessage.visibility = View.GONE
            holder.chatReceivedImage.downloadAndSetImage(mListMessagesCache[position].fileUrl)
            holder.chatReceivedImageMessageTime.text = mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    private fun drawMessageText(holder: SingleChatHolder, position: Int) {
        holder.blockReceivedImageMessage.visibility = View.GONE
        holder.blockUserImageMessage.visibility = View.GONE

        if(mListMessagesCache[position].from == CURRENT_UID){
            holder.blockUserMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.visibility = View.GONE
            holder.chatUserMessage.text = mListMessagesCache[position].text
            holder.chatUserMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        } else {
            holder.blockReceivedMessage.visibility = View.GONE
            holder.chatReceivedMessage.visibility = View.VISIBLE
            holder.chatReceivedMessage.text = mListMessagesCache[position].text
            holder.chatReceivedMessageTime.text =
                mListMessagesCache[position].timeStamp.toString().asTime()
        }
    }

    override fun getItemCount(): Int = mListMessagesCache.size

   fun addItemToBottom(
       item: CommonModel,
       onSuccess: ()->Unit
   ){
       if (!mListMessagesCache.contains(item)){
           mListMessagesCache.add(item)
           notifyItemInserted(mListMessagesCache.size)
       }
       onSuccess()
   }

    fun addItemToTop(
        item: CommonModel,
        onSuccess: ()->Unit
    ){
        if (!mListMessagesCache.contains(item)){
            mListMessagesCache.add(item)
            mListMessagesCache.sortBy { it.timeStamp.toString() }
            notifyItemInserted(0)
        }
        onSuccess()
    }
}


