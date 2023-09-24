package com.sidharth.vidyakhoj.di

import com.sidharth.vidyakhoj.data.UniversityApi
import com.sidharth.vidyakhoj.data.UniversityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideUniversityRepository(
        universityApi: UniversityApi
    ): UniversityRepository {
        return UniversityRepository(universityApi)
    }
}