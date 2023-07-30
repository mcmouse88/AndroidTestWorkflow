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
import java.lang.AssertionError

class WrapExceptionTest2 {

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

    @Test
    fun `wrapException execute throws IOException`() = runTest {
        val block = createdMockedBlock()
        val expectedException = IOException()
        coEvery { block.invoke() } throws  expectedException
        val networkException = catch<NetworkException> { wrapExceptionClass.wrapException(block) }
        Assert.assertSame(expectedException, networkException.e)
    }

    @Test
    fun `wrapException execute throws any exception`() = runTest {
        val block = createdMockedBlock()
        val expectedException = IllegalStateException()
        coEvery { block.invoke() } throws expectedException
        val unknownError = catch<UnknownException> { wrapExceptionClass.wrapException(block) }
        Assert.assertSame(expectedException, unknownError.e)
    }

    @Test
    fun `wrapException execute throws HttpException exception`() = runTest {
        val block = createdMockedBlock()
        val httpException = mockk<HttpException>()
        val response = mockk<Response<*>>()
        val errorBody = mockk<ResponseBody>()
        val errorJson = "{\"error\" : \"Description\"}"
        every { httpException.response() } returns response
        every { httpException.code() } returns 401
        every { response.errorBody() } returns errorBody
        every { errorBody.string() } returns errorJson
        coEvery { block.invoke() } throws httpException
        val serverError = catch<ServerException> { wrapExceptionClass.wrapException(block) }
        Assert.assertEquals("Description", serverError.errorMessage)
        Assert.assertEquals(401, serverError.code)
    }

    private fun createdMockedBlock(): suspend () -> String {
        return mockk()
    }

    inline fun<reified T : Throwable> catch(block: () -> Unit): T {
        try {
            block()
        } catch(e: Throwable) {
            if (e is T) {
                return e
            } else {
                Assert.fail("Invalid exception type. " +
                        "Expected: ${T::class.java.simpleName}, " +
                        "Actual: ${e::class.java.simpleName}")
            }
        }
        throw AssertionError("No expected exception")
    }
}