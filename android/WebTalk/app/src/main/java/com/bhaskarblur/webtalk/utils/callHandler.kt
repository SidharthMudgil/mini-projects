package com.bhaskarblur.webtalk.utils

import com.bhaskarblur.webtalk.model.callModel

enum class callTypes {
    StartedAudioCall,StartedVideoCall,Offer,FinalAnswer, Answer,Reject,ICECandidate,EndCall
}
interface callHandler {

    fun onCallReceived(message : callModel);

    fun onInitOffer(message : callModel);
    fun onCallAccepted(message : callModel);

    fun onCallRejected(message: callModel);

    fun onCallCut(message : callModel);

    fun onUserAdded(message : callModel);

    fun finalCallAccepted(message : callModel);


}
