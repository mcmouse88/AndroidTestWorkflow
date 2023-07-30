package com.mcmouse88.hrenapplication

import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class SignInRepositoryTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var api: SignInApi

    @Test
    fun `sign in returns token`() = runTest {
        val wrapper = WrapException()
        val repository = SignInRepository(api, wrapper)
        val request = SignInRequest("login", "password")
        coEvery { api.signIn(request) } returns SignInResponse("token")

        val token = repository.signIn("login", "password")
        Assert.assertEquals("token", token)
        coVerify(exactly = 1) { api.signIn(request) }
        confirmVerified(api)
    }

    @Test
    fun `signIn was called in wrapException`() = runTest {
        val wrapper = WrapException()
        val spy = spyk(wrapper)
        val repository = SignInRepository(api, spy)
        val request = SignInRequest("login", "password")
        val slot = slot<suspend () -> String>()
        coEvery { api.signIn(request) } returns SignInResponse("token")
        coEvery { spy.wrapException(capture(slot)) } returns ""
        repository.signIn("login", "password")
        val token = slot.captured.invoke()
        Assert.assertEquals("token", token)
    }
}