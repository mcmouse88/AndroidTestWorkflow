package com.mcmouse88.hrenapplication

import kotlinx.coroutines.flow.MutableStateFlow

class SomeRepository {

    val flow = MutableStateFlow(0)

    fun getSomeThing(param: Int) {
        if (param == 0) {
            flow.tryEmit(15)
        } else {
            flow.tryEmit(25)
        }
    }
}