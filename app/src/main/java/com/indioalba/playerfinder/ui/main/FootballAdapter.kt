package com.indioalba.playerfinder.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_load_more.view.*
import kotlinx.android.synthetic.main.item_player.view.*
import kotlinx.android.synthetic.main.item_team.view.*
import kotlin.properties.Delegates


class FootballAdapter(private val listener: (Item) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items: List<Item> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = old.size
            override fun getNewListSize(): Int = new.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition].id == new[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return old[oldItemPosition] === new[newItemPosition]
            }
        }).dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return items[position].layout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = parent.inflate(viewType, false)
        return when (viewType) {
            Item.Team.layout -> TeamViewHolder(view)
            Item.Header.layout -> HeaderViewHolder(view)
            Item.Player.layout -> PlayerViewHolder(view)
            Item.LoadMore.layout -> LoadMoreViewHolder(view)
            Item.NotFound.layout -> NotFoundViewHolder(view)
            else -> NotFoundViewHolder(view)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener { listener(item) }

        when (getItemViewType(position)) {
            Item.Header.layout -> (holder as HeaderViewHolder).bind(item)
            Item.Player.layout -> (holder as PlayerViewHolder).bind(item)
            Item.Team.layout -> (holder as TeamViewHolder).bind(item)
            Item.LoadMore.layout -> (holder as LoadMoreViewHolder).bind(item)
            Item.NotFound.layout -> (holder as NotFoundViewHolder).bind(item)
        }
    }

    private fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

    abstract class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(item: Item)
    }

    private class HeaderViewHolder(itemView: View) : ItemViewHolder(itemView) {
        override fun bind(item: Item) {
            (item as Item.Header).let {
                itemView.headerTitle.text = it.title
            }
        }
    }

    private class PlayerViewHolder(itemView: View) : ItemViewHolder(itemView) {
        override fun bind(item: Item) {
            with((item as Item.Player).player) {
                itemView.playerName.text = playerFirstName + " " + playerSecondName
                itemView.playerAge.text = playerAge
                itemView.playerClub.text = playerClub
            }
        }
    }

    private class TeamViewHolder(itemView: View) : ItemViewHolder(itemView) {
        override fun bind(item: Item) {
            (item as Item.Team).let {
                with(it.team) {
                    itemView.teamName.text = teamName
                    itemView.teamCity.text = teamCity
                    itemView.teamStadium.text = teamStadium
                }
            }
        }
    }

    private class NotFoundViewHolder(itemView: View) : ItemViewHolder(itemView) {
        override fun bind(item: Item) {}
    }

    private class LoadMoreViewHolder(itemView: View) : ItemViewHolder(itemView) {
        override fun bind(item: Item) {
            (item as Item.LoadMore).let {
                itemView.loadMoreButton.text = it.title
            }
        }
    }
}