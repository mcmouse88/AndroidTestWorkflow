package com.mcmouse88.hrenapplication

import retrofit2.http.Body
import retrofit2.http.POST

interface SignInApi {
    @POST("sign-in")
    suspend fun signIn(@Body body: SignInRequest): SignInResponse
}

data class SignInRequest(val login: String, val password: String)

data class SignInResponse(val token: String)