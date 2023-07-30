package com.mcmouse88.hrenapplication.entity

import com.mcmouse88.hrenapplication.UserStatus
import org.junit.Assert
import org.junit.Test

class UserStatusTest {

    @Test
    fun `parse user status active`() {
        val userStatus = UserStatus.parse("Active")
        val expectedStatus = UserStatus.Active
        Assert.assertEquals(userStatus, expectedStatus)
    }

    @Test
    fun `parse user status banned`() {
        val userStatus = UserStatus.parse("Banned")
        val expectedStatus = UserStatus.Banned
        Assert.assertEquals(userStatus, expectedStatus)
    }

    @Test
    fun `parse user status undefined`() {
        val userStatus = UserStatus.parse("Abra Cadabra")
        val expectedStatus = UserStatus.Banned
        Assert.assertEquals(userStatus, expectedStatus)
    }
}