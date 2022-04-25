package com.sevdotdev.statshotrebound.data.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AlternateHostInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val host = if (request.url().host().contains("localhost")){
            "6ad8-2606-9400-8d9f-fed5-85a8-6a7a-ff03-a2e6.ngrok.io"
        } else {request.url().host()}
        val newHttpUrl = request.url().newBuilder()
            .scheme("https")
            .host(host)
            .build()
        request = request.newBuilder()
            .url(newHttpUrl)
            .build()
        return chain.proceed(request)

    }
}