package com.mcmouse88.hrenapplication

class SomethingClass(
    private val list: MutableList<String>
) : List<String> by list {

    override val size: Int = 500 * list.size

    override fun get(index: Int): String {
        return "No value, I'm just an override method"
    }
}

