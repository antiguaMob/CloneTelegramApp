package com.antigua.mytelegram.ui.message_recycler_view.view_holders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.antigua.mytelegram.database.CURRENT_UID
import com.antigua.mytelegram.ui.message_recycler_view.views.MessageView
import com.antigua.mytelegram.utilits.asTime
import com.antigua.mytelegram.utilits.downloadAndSetImage
import kotlinx.android.synthetic.main.message_item_image.view.*

class HolderImageMessage(view: View): RecyclerView.ViewHolder(view),MessageHolder {
    //Image
    val blockReceivedImageMessage: ConstraintLayout = view.block_received_image_message
    val blockUserImageMessage: ConstraintLayout = view.block_user_image_message
    val chatUserImage: ImageView =  view.chat_user_image
    val chatReceivedImage: ImageView =  view.chat_received_image
    val chatUserImageMessageTime: TextView = view.chat_user_image_massage_time
    val chatReceivedImageMessageTime: TextView = view.chat_received_image_massage_time

    override fun drawMessage(view: MessageView) {
        if(view.from == CURRENT_UID){
            blockReceivedImageMessage.visibility = View.GONE
            blockUserImageMessage.visibility = View.VISIBLE
            chatUserImage.downloadAndSetImage(view.fileUrl)
            chatUserImageMessageTime.text = view.timeStamp.asTime()
        } else {
            blockReceivedImageMessage.visibility = View.VISIBLE
            blockUserImageMessage.visibility = View.GONE
            chatReceivedImage.downloadAndSetImage(view.fileUrl)
            chatReceivedImageMessageTime.text = view.timeStamp.asTime()
        }
    }
}