package com.sidharth.navachar.di

import com.sidharth.navachar.data.remote.RemoteDataSource
import com.sidharth.navachar.data.repository.FeedRepositoryImpl
import com.sidharth.navachar.domain.repository.FeedRepository
import com.sidharth.navachar.domain.usecase.GetPostsUseCase
import com.sidharth.navachar.domain.usecase.GetPostsUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    @Provides
    @Singleton
    fun provideFeedRepository(dataSource: RemoteDataSource): FeedRepository {
        return FeedRepositoryImpl(dataSource)
    }

    @Provides
    @Singleton
    fun provideGetPostsUseCase(repository: FeedRepository): GetPostsUseCase {
        return GetPostsUseCaseImpl(repository)
    }
}