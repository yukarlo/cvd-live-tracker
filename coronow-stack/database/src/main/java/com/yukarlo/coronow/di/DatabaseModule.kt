package com.yukarlo.coronow.di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.yukarlo.coronow.stack.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideSqlDriver(@ApplicationContext context: Context): SqlDriver = AndroidSqliteDriver(
        schema = Database.Schema,
        context = context,
        name = "cvdDatabase.db"
    )

//    @Provides
//    @Singleton
//    fun provideDatabase(sqlDriver: SqlDriver): Database = Database(sqlDriver)
}
