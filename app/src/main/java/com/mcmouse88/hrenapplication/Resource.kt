package com.mcmouse88.hrenapplication

import java.util.concurrent.Executor

class Resource(
    val executor: Executor,
    val errorHandler: ErrorHandler
) {
    fun execute() {
        try {
            executor.execute {
                println()
            }
        } catch (e: Exception) {
            errorHandler.handle(e)
        }
    }

    fun printBullshit() {
        println("something Bullshit")
    }
}