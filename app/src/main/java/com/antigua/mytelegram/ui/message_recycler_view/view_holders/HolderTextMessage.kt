package com.antigua.mytelegram.ui.message_recycler_view.view_holders

import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.antigua.mytelegram.database.CURRENT_UID
import com.antigua.mytelegram.ui.message_recycler_view.views.MessageView
import com.antigua.mytelegram.utilits.asTime
import kotlinx.android.synthetic.main.message_item_text.view.*

class HolderTextMessage(view: View): RecyclerView.ViewHolder(view),MessageHolder {
    //Text
    val blockUserMessage : ConstraintLayout = view.block_user_message
    val chatUserMessage : TextView = view.chat_user_message
    val chatUserMessageTime : TextView = view.chat_user_massage_time
    val blockReceivedMessage : ConstraintLayout = view.block_received_message
    val chatReceivedMessage : TextView = view.chat_received_message
    val chatReceivedMessageTime : TextView = view.chat_received_massage_time

    override fun drawMessage(view: MessageView) {
        if(view.from == CURRENT_UID){
            blockUserMessage.visibility = View.VISIBLE
            chatReceivedMessage.visibility = View.GONE
            chatUserMessage.text = view.text
            chatUserMessageTime.text =
                view.timeStamp.asTime()
        } else {
            blockReceivedMessage.visibility = View.GONE
            chatReceivedMessage.visibility = View.VISIBLE
            chatReceivedMessage.text = view.text
            chatReceivedMessageTime.text =
                view.timeStamp.asTime()
        }
    }
}