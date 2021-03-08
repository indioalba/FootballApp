package com.indioalba.playerfinder.repository

import com.indioalba.domain.FootballItems
import com.indioalba.playerfinder.repository.service.FootballService
import javax.inject.Inject

interface Repository {
    suspend fun searchTeamsAndClubs(query: String): FootballItems
    suspend fun searchPlayers(): FootballItems
    suspend fun searchTeams(): FootballItems
}

class ItemListRepository @Inject constructor(private val footballService: FootballService) :
    Repository {

    override suspend fun searchTeamsAndClubs(query: String): FootballItems {
        // here it is where we should consider if we want to fetch the data locally or remotely
        // we would have to inject a class that would handle the access to the DB
        return footballService.retrieveFootballElements(query)
    }

    override suspend fun searchPlayers(): FootballItems {
        return footballService.retrieveMorePlayers()
    }

    override suspend fun searchTeams(): FootballItems {
        return footballService.retrieveMoreTeams()
    }
}