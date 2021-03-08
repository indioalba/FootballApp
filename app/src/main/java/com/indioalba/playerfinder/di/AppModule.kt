package com.indioalba.playerfinder.di

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.hilt.lifecycle.ViewModelFactoryModules
import com.indioalba.playerfinder.BaseApplication
import com.indioalba.playerfinder.R
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule  {

    @Provides
    @Singleton
    @Named("apiKey")
    fun apiKeyProvider(app: Application): String = app.getString(R.string.api_key)

}