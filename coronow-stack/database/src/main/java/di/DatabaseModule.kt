package di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.yukarlo.coronow.stack.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Provides
    fun provideSqlDriver(context: Context): SqlDriver = AndroidSqliteDriver(
        schema = Database.Schema,
        context = context,
        name = "cvdDatabase.db"
    )

    @Provides
    fun provideDatabase(sqlDriver: SqlDriver): Database = Database(sqlDriver)
}
