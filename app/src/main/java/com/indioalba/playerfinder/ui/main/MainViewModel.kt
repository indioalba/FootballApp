package com.indioalba.playerfinder.ui.main

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indioalba.domain.FootballItems
import com.indioalba.domain.FootballPlayer
import com.indioalba.domain.FootballTeam
import com.indioalba.playerfinder.repository.ItemListRepository
import com.indioalba.playerfinder.repository.Repository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MainViewModel @ViewModelInject constructor(
    private val itemListFactory: ItemListFactory,
    private val repository: Repository
) : ViewModel() {

    private val _items: MutableLiveData<List<Item>> = MutableLiveData(emptyList())
    val item: LiveData<List<Item>> = _items

    fun search(query: String) {
        viewModelScope.launch {
            val footballItems = repository.searchTeamsAndClubs(query)
            createItemList(footballItems)
        }
    }

    fun onItemClicked(item: Item) {
        // action like open detail view or make another request (more player, more teams)
        Log.e("onItemClicked", "not implemented yet")
    }

    private fun createItemList(footballItems: FootballItems) {
        viewModelScope.launch {
            _items.value = itemListFactory.createItemList(footballItems)
        }
    }

}