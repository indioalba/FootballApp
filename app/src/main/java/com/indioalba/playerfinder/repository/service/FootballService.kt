package com.indioalba.playerfinder.repository.service

import com.indioalba.domain.FootballItems
import javax.inject.Inject

class FootballService @Inject constructor(private val searchRequestBodyFactory: SearchRequestBodyFactory) {

    // Those values should be updated accordingly to the number of players and teams we get. everytime.
    // if retrieveFootballElements get called in a second time, the offset values should be set to 0 again
    private var playersOffset = 0
    private var teamsOffset = 0
    private var query = ""

    suspend fun retrieveFootballElements(query: String): FootballItems {
        this.query = query
        playersOffset = 0
        teamsOffset = 0
        val response =
            RetrofitInstance.service.postSearch(searchRequestBodyFactory.createBody(query))
        // we should handle the error scenario or if the response is empty -> if(response.isSuccessful) ....
        return response.body()!!.result
    }

    suspend fun retrieveMorePlayers(): FootballItems {
        val response = RetrofitInstance.service.postSearch(
            searchRequestBodyFactory.createBody(
                query,
                "players",
                playersOffset
            )
        )
        // we should handle the error scenario or if the response is empty -> if(response.isSuccessful) ....
        return response.body()!!.result
    }

    suspend fun retrieveMoreTeams(): FootballItems {
        val response = RetrofitInstance.service.postSearch(
            searchRequestBodyFactory.createBody(
                query,
                "teams",
                teamsOffset
            )
        )
        // we should handle the error scenario or if the response is empty -> if(response.isSuccessful) ....
        return response.body()!!.result
    }
}