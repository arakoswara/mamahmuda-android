package com.diagnosabanding.helper

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    val client : OkHttpClient = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
    }.build()

    fun initRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl("https://all-api.herokuapp.com/public/diagnosis/")
            .baseUrl("http://192.168.0.103:8000/diagnosis/")
            .client(client)
            .build()
    }

    fun <T> createService(service: Class<T>) : T {
        return initRetrofit().create(service)
    }
}