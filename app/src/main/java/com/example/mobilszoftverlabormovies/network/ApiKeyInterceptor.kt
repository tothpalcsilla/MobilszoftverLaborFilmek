package com.example.mobilszoftverlabormovies.network

import com.example.mobilszoftverlabormovies.Config
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().header("api_key", Config.API_KEY).build()
        return chain.proceed(request)
    }
}
