package com.yukarlo.coronow.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.yukarlo.coronow.cvdDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideSqlDriver(@ApplicationContext context: Context): SqlDriver = AndroidSqliteDriver(
        schema = cvdDatabase.Schema,
        context = context,
        name = "cvdDatabase.db"
    )

    @Provides
    @Singleton
    fun provideDatabase(sqlDriver: SqlDriver): cvdDatabase = cvdDatabase(driver = sqlDriver)
}
