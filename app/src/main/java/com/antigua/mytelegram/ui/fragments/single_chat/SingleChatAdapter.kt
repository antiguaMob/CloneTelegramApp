package com.antigua.mytelegram.ui.fragments.single_chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.DiffResult
import androidx.recyclerview.widget.RecyclerView
import com.antigua.mytelegram.R
import com.antigua.mytelegram.models.CommonModel
import com.antigua.mytelegram.utilits.CURRENT_UID
import com.antigua.mytelegram.utilits.DiffUtilCallback
import com.antigua.mytelegram.utilits.asTime
import kotlinx.android.synthetic.main.message_item.view.*

class SingleChatAdapter : RecyclerView.Adapter<SingleChatAdapter.SingleChatHolder>() {

    private var mListMessagesCache = emptyList<CommonModel>()
    private lateinit var mDiffResult: DiffResult

    class SingleChatHolder(view: View) : RecyclerView.ViewHolder(view){
        val blockUserMessage : ConstraintLayout = view.block_user_message
        val chatUserMessage : TextView = view.chat_user_message
        val chatUserMessageTime : TextView = view.chat_user_massage_time

        val blockReceivedMessage : ConstraintLayout = view.block_received_message
        val chatReceivedMessage : TextView = view.chat_received_message
        val chatReceivedMessageTime : TextView = view.chat_received_massage_time


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleChatHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent,false)
        return  SingleChatHolder(view)
    }

    override fun onBindViewHolder(holder: SingleChatHolder, position: Int) {
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

    fun addItem(item: CommonModel){
        val newList = mutableListOf<CommonModel>()
        newList.addAll(mListMessagesCache)
        if(!newList.contains(item)){ newList.add(item) }
        newList.sortBy { it.timeStamp.toString() }
        mDiffResult = DiffUtil.calculateDiff(DiffUtilCallback(mListMessagesCache,newList))
        mDiffResult.dispatchUpdatesTo(this)
        mListMessagesCache = newList
    }
}


