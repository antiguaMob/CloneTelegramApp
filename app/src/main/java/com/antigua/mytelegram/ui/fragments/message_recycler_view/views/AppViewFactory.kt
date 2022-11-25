package com.antigua.mytelegram.ui.fragments.message_recycler_view.views

import com.antigua.mytelegram.models.CommonModel
import com.antigua.mytelegram.utilits.AppConstants.TYPE_MESSAGE_IMAGE
import com.antigua.mytelegram.utilits.AppConstants.TYPE_MESSAGE_VOICE

class AppViewFactory {
    companion object{
        fun getView(message: CommonModel): MessageView{
            return  when(message.type){
                TYPE_MESSAGE_IMAGE -> ViewImageMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.fileUrl
                )
                TYPE_MESSAGE_VOICE -> ViewVoiceMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.fileUrl
                )
                else -> ViewTextMessage(
                    message.id,
                    message.from,
                    message.timeStamp.toString(),
                    message.fileUrl,
                    message.text
                )
            }
        }
    }
}