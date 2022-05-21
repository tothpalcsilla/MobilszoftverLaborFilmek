package com.example.mobilszoftverlabormovies.network

import com.example.mobilszoftverlabormovies.Config
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest.newBuilder().header("api_key", Config.API_KEY).build()
        println("ddddddddddddddddddddddddddddddddddddddddddddd 1 ${request}")
        //request = originalRequest.newBuilder().url(originalRequest.url()).build()
        //println("ddddddddddddddddddddddddddddddddddddddddddddd 2 ${request}")
        return chain.proceed(request)
    }
}
