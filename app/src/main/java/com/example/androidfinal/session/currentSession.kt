package com.example.androidfinal.session

object currentSession {
    var role = "CLIENT"
    var id = ""
//    var email = ""
//    var password = ""

    fun initialize(role: String, id: String){
        this.role = role
        this.id = id
//        this.email = email
//        this.password = password
    }
}