package di

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.yukarlo.coronow.stack.database.Database
import dagger.Module
import dagger.Provides

@Module
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
