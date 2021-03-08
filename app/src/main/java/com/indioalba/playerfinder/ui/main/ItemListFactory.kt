package com.indioalba.playerfinder.ui.main

import com.indioalba.domain.FootballItems
import com.indioalba.playerfinder.R
import com.indioalba.playerfinder.utilities.ResourceProvider
import javax.inject.Inject

class ItemListFactory @Inject constructor(private val resourceProvider: ResourceProvider) {

    fun createItemList(footballItems: FootballItems): List<Item> {

        val itemList = mutableListOf<Item>()

        if (footballItems.players.isNullOrEmpty() && footballItems.teams.isNullOrEmpty()) {
            itemList.add(Item.NotFound())
        } else {
            if (!footballItems.players.isNullOrEmpty()) {
                itemList.add(Item.Header(resourceProvider.getString(R.string.player_title)))
                footballItems.players?.forEach {
                    itemList.add(Item.Player(it))
                }
                itemList.add(Item.LoadMore(resourceProvider.getString(R.string.load_more_players)))
            }
            if (!footballItems.teams.isNullOrEmpty()) {
                itemList.add(Item.Header(resourceProvider.getString(R.string.team_title)))
                footballItems.teams?.forEach {
                    itemList.add(Item.Team(it))
                }
                itemList.add(Item.LoadMore(resourceProvider.getString(R.string.load_more_teams)))
            }
        }
        return itemList
    }
}