package com.bhaskarblur.webtalk.utils

class helper {

    fun cleanWord(word: String) : String {
        return word.toString().replace(".","").
                replace("#","").
        replace("$","").
                replace("[","").replace("]","");
    }
}