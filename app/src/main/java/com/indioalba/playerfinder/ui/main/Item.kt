package com.indioalba.playerfinder.ui.main

import androidx.annotation.LayoutRes
import com.indioalba.domain.FootballPlayer
import com.indioalba.domain.FootballTeam
import com.indioalba.playerfinder.R

sealed class Item(@LayoutRes val layout: Int, val id: String) {

    class Header(val title: String) : Item(layout, title) {
        companion object {
            const val layout = R.layout.item_header
        }
    }

    class Player(val player: FootballPlayer) : Item(layout, player.playerID){
        companion object {
            const val layout = R.layout.item_player
        }
    }

    class Team(val team: FootballTeam) : Item(layout, team.teamID) {
        companion object {
            const val layout = R.layout.item_team
        }
    }

    class NotFound() : Item(layout, "notFoundId"){
        companion object {
            const val layout = R.layout.item_no_results_found
        }
    }

    class LoadMore(val title: String) : Item(layout, title){
        companion object {
            const val layout = R.layout.item_load_more
        }
    }
}