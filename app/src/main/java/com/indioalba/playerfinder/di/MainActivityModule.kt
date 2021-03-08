package com.indioalba.playerfinder.di

import com.indioalba.playerfinder.repository.ItemListRepository
import com.indioalba.playerfinder.repository.Repository
import com.indioalba.playerfinder.repository.service.FootballService
import com.indioalba.playerfinder.ui.main.ItemListFactory
import com.indioalba.playerfinder.ui.main.MainViewModel
import com.indioalba.playerfinder.utilities.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
class MainActivityModule {

    @Provides
    fun providesViewModel(resourceProvider: ResourceProvider, footballService: FootballService) =
        MainViewModel(
            getItemListFactory(resourceProvider),
            getItemListRepository(footballService)
        )

    @Provides
    fun getItemListFactory(resourceProvider: ResourceProvider): ItemListFactory {
        return ItemListFactory(resourceProvider)
    }

    @Provides
    fun getItemListRepository(footballService: FootballService): Repository {
        return ItemListRepository(footballService)
    }
}