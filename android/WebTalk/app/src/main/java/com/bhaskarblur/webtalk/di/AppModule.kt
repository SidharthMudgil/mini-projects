package com.bhaskarblur.webtalk.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class appModule {

    @Provides
    fun provideContext(@ApplicationContext context: Context) : Context = context.applicationContext
}