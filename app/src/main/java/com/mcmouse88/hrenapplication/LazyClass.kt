package com.mcmouse88.hrenapplication

import android.util.Log

class LazyClass {

    fun printHashCode() {
        Log.e("TAG_CHECK", hashCode().toString())
    }
}