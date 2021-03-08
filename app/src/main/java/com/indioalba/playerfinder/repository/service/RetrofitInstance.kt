package com.indioalba.playerfinder.repository.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://trials.mtcmobile.co.uk/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: FootballApi by lazy { retrofit.create(FootballApi::class.java) }
}