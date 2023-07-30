package com.mcmouse88.hrenapplication

import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.junit4.MockKRule
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class WrapExceptionTest {

    @get:Rule
    val rule = MockKRule(this)

    @InjectMockKs
    lateinit var wrapExceptionClass: WrapException

    @Test
    fun `wrapException execute success`() = runTest {
        val block = createdMockedBlock()
        coEvery { block.invoke() } returns "test"
        val result = wrapExceptionClass.wrapException { block.invoke() }
        Assert.assertEquals("test", result)
    }

    @Test(expected = NetworkException::class)
    fun `wrapException execute throws IOException`() = runTest {
        val block = createdMockedBlock()
        coEvery { block.invoke() } throws IOException()
        wrapExceptionClass.wrapException { block.invoke() }
    }

    @Test(expected = UnknownException::class)
    fun `wrapException execute throws any exception`() = runTest {
        val block = createdMockedBlock()
        coEvery { block.invoke() } throws IllegalStateException()
        wrapExceptionClass.wrapException { block.invoke() }
    }

    @Test(expected = ServerException::class)
    fun `wrapException execute throws HttpException exception`() = runTest {
        val block = createdMockedBlock()
        val httpException = mockk<HttpException>()
        val response = mockk<Response<*>>()
        val errorBody = mockk<ResponseBody>()
        val errorJson = "{\"Error!\" : \"Description\"}"
        every { httpException.response() } returns response
        every { httpException.code() } returns 401
        every { response.errorBody() } returns errorBody
        every { errorBody.string() } returns errorJson
        coEvery { block.invoke() } throws httpException
        wrapExceptionClass.wrapException { block.invoke() }
    }

    private fun createdMockedBlock(): suspend () -> String {
        return mockk()
    }
}