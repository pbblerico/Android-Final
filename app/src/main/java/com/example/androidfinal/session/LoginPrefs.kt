package com.example.androidfinal.session

import android.content.Context
import android.content.SharedPreferences

class LoginPrefs {

    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val cont: Context
    private var PRIVATEMODE: Int = 0

    constructor(cont: Context) {
        this.cont = cont
        this.pref = cont.getSharedPreferences(PREF_NAME, PRIVATEMODE)
        this.editor = pref.edit()
    }

    companion object {
        const val PREF_NAME = "Login_Preferences"
        const val IS_LOGIN = "isLoggedIn"
        const val KEY_EMAIl = "email"
        const val KEY_USERNAME = "username"
        const val KEY_PASSWORD = "password"
        const val KEY_ROLE = "role"
        const val KEY_ID = "id"
    }

    fun createLoginSession(email: String, password: String, id: String, role: String, username: String) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_EMAIl, email)
        editor.putString(KEY_ID, id)
        editor.putString(KEY_PASSWORD, password)
        editor.putString(KEY_ROLE, role)
        editor.putString(KEY_USERNAME, username)
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

    fun getUserRole(): String {
        return pref.getString(KEY_ROLE, "CLIENT")!!
    }

    fun logoutUser() {
        editor.clear()
        editor.commit()
    }

    fun isLoggedIn(): Boolean {
        return pref.getBoolean(IS_LOGIN, false)
    }
}