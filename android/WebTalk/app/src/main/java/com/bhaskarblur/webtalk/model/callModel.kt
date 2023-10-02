package com.bhaskarblur.webtalk.model

 class callModel {

    var senderEmail : String? = null
    var senderName : String? = null
    var targetEmail : String? = null
    var callData : String? = null
    var timeStamp : Long = System.currentTimeMillis();
    lateinit var callType : String;


    constructor(
        senderEmail: String?,
        senderName: String?,
        targetEmail: String?,
        callData: String?,
        callType: String
    ) {
        this.senderEmail = senderEmail
        this.senderName = senderName
        this.targetEmail = targetEmail
        this.callData = callData
        this.callType = callType
    }

     constructor(
         senderEmail: String?,
         senderName: String?,
         targetEmail: String?,
         callType: String
     ) {
         this.senderEmail = senderEmail
         this.senderName = senderName
         this.targetEmail = targetEmail
         this.callType = callType
     }
    constructor()


}
fun callModel.isValid(): Boolean {
    return System.currentTimeMillis() - this.timeStamp < 60000;
}