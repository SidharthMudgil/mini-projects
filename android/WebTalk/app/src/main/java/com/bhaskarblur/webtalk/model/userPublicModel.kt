package com.bhaskarblur.webtalk.model

class userPublicModel {

     var username: String = "";
     var email: String = "";
    var status : String = ""
    var pushToken : String = ""

    constructor(username: String, email: String,password: String) {
        this.username = username
        this.email = email
    }

    constructor(username: String, email: String, password: String, status : String, pushToken : String) {
        this.username = username
        this.email = email
        this.status = status
        this.pushToken = pushToken
    }
    constructor() {

    }
}