package test_tasks.workmate_test_task.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test_tasks.workmate_test_task.data.repository.StarWarsRepositoryImpl
import test_tasks.workmate_test_task.domain.repository.StarWarsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStarWarsRepository(
        starWarsRepositoryImpl: StarWarsRepositoryImpl
    ): StarWarsRepository
}
