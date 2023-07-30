package com.mcmouse88

import android.content.Context
import android.content.SharedPreferences
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.verifySequence
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserSettingsTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var context: Context

    @MockK
    lateinit var pref: SharedPreferences

    @RelaxedMockK
    lateinit var editor: SharedPreferences.Editor

    private lateinit var settings: UserSettings

    private val tokenKey = "pref_token"

    @Before
    fun setUp() {
        every { context.getSharedPreferences(any(), any()) } returns pref
        every { pref.edit() } returns editor
        settings = UserSettings(context)
    }

    @Test
    fun `get token from settings test`() {
        every { pref.getString(tokenKey, any()) } returns "token"
        val token = settings.getToken()
        Assert.assertEquals("token", token)
    }

    @Test
    fun `set token in settings`() {
        settings.setToken("token")
        verifySequence {
            pref.edit()
            editor.putString(tokenKey, "token")
            editor.apply()
        }
    }

    @Test
    fun `set token value null and clear old token`() {
        settings.setToken(null)
        verifySequence {
            pref.edit()
            editor.remove(tokenKey)
            editor.apply()
        }
    }
}