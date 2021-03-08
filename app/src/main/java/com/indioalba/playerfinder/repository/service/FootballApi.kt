package com.indioalba.playerfinder.repository.service

import com.indioalba.domain.FootballResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface FootballApi {

    @POST("api/football/1.0/search")
    suspend fun postSearch(@Body requestBody: SearchRequestBody): Response<FootballResponse>

}