package com.example.androidfinal.session

import android.content.Context
import android.content.SharedPreferences

class LoginPrefs {

    lateinit var pref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    val cont: Context
    var PRIVATEMODE: Int = 0

    constructor(cont: Context) {
        this.cont = cont
        this.pref = cont.getSharedPreferences(PREF_NAME, PRIVATEMODE)
        this.editor = pref.edit()
    }

    companion object {
        val PREF_NAME = "Login_Preferences"
        val IS_LOGIN = "isLoggedIn"
        val KEY_EMAIl = "email"
        val KEY_PASSWORD = "password"
        val KEY_ROLE = "role"
        val KEY_ID = "id"
    }

    fun createLoginSession(email: String, password: String, id: String, role: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_EMAIl, email)
        editor.putString(KEY_ID, id)
        editor.putString(KEY_PASSWORD, password)
        editor.putString(KEY_ROLE, role)
        editor.commit()
    }

    fun getUserDetails(): HashMap<String, String> {
        var user: Map<String, String> = HashMap()
        (user as HashMap)[KEY_EMAIl] = pref.getString(KEY_EMAIl, null)!!
        (user as HashMap)[KEY_ID] = pref.getString(KEY_ID, null)!!
        (user as HashMap)[KEY_PASSWORD] = pref.getString(KEY_PASSWORD, null)!!
        (user as HashMap)[KEY_ROLE] = pref.getString(KEY_ROLE, null)!!

        return user
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }
}