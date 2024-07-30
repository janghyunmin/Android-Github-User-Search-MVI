package com.kkjang.data.di

import com.kkjang.data.datasource.SearchDataSourceImpl
import com.kkjang.data.local.UserDataBaseImpl
import com.kkjang.domain.datasource.SearchDataSource
import com.kkjang.domain.datasource.UserDataBase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindUserDataBase(userDataBaseImpl: UserDataBaseImpl): UserDataBase

    @Binds
    @Singleton
    abstract fun bindSearchDataSource(searchDataSourceImpl: SearchDataSourceImpl): SearchDataSource
}