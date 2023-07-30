package com.mcmouse88

import android.content.Context

class UserSettings(context: Context) {
    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun getToken(): String? {
        return prefs.getString(PREF_TOKEN, null)
    }

    fun setToken(token: String?) {
        val editor = prefs.edit()
        if (token == null) editor.remove(PREF_TOKEN)
        else editor.putString(PREF_TOKEN, token)
        editor.apply()
    }

    companion object {
        private const val PREF_NAME = "settings"
        private const val PREF_TOKEN = "pref_token"
    }
}