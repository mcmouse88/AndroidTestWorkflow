package com.mcmouse88.hrenapplication

class SignInRepository(
    private val api: SignInApi,
    private val wrapper: WrapException
) {
    suspend fun signIn(login: String, password: String): String {
        return wrapper.wrapException {
            val request = SignInRequest(login, password)
            api.signIn(request).token
        }
    }
}