package com.mcmouse88.hrenapplication

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException

class WrapException {

    suspend fun <T> wrapException(block: suspend () -> T): T {
        return try {
            block()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> throw createBackendException(e)
                is IOException -> throw NetworkException(e)
                else -> throw UnknownException(e)
            }

        }
    }

    private fun createBackendException(e: HttpException): Exception {
        return try {
            val errorBody = e.response()?.errorBody()?.string()
                ?: throw UnknownException(IllegalStateException())
            val errorJsonBody = Gson().fromJson(errorBody, ErrorJsonBody::class.java)
            ServerException(e.code(), errorJsonBody.error)
        } catch (e: Exception) {
            throw UnknownException(e)
        }
    }
}

class NetworkException(val e: Exception) : RuntimeException()

class UnknownException(val e: Exception) : RuntimeException()

class ServerException(val code: Int, val errorMessage: String) : RuntimeException()

class ErrorJsonBody(
    val error: String
)