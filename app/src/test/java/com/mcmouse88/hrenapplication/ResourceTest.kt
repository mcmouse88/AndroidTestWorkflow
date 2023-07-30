package com.mcmouse88.hrenapplication

import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit4.MockKRule
import io.mockk.slot
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.Executor

class ResourceTest {

    @get:Rule
    val rule = MockKRule(this)

    @MockK
    lateinit var executor: Executor

    @RelaxedMockK
    lateinit var errorHandler: ErrorHandler

    @InjectMockKs
    lateinit var resource: Resource

    @Test
    fun test() {
       val expectedException = IllegalStateException("something went wrong")
        every { executor.execute(any()) } throws expectedException
        resource.execute()
        verify(exactly = 1) { errorHandler.handle(refEq(expectedException)) }
        confirmVerified(errorHandler)
    }
}