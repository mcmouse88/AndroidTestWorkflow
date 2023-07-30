package com.mcmouse88.hrenapplication

import io.mockk.junit4.MockKRule
import org.junit.Rule

open class ViewModelTest {

    @get:Rule
    val testModelScopeRule = TestViewModelScopeRule()

    @get:Rule
    val mockKRule = MockKRule(this)
}