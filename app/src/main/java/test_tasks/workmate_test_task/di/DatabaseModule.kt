package test_tasks.workmate_test_task.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import test_tasks.workmate_test_task.data.datasources.local.AppDatabase
import test_tasks.workmate_test_task.data.datasources.local.dao.StarWarsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "star_wars_db"
        ).build()
    }

    @Provides
    fun provideStarWarsDao(database: AppDatabase): StarWarsDao {
        return database.starWarsDao()
    }
}
