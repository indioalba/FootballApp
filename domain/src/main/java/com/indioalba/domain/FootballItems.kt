package com.indioalba.domain

data class FootballResponse(val result: FootballItems)

data class FootballItems(
    val players: List<FootballPlayer>?,
    val teams: List<FootballTeam>?
)