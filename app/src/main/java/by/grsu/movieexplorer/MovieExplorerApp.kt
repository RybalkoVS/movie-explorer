package by.grsu.movieexplorer

import android.app.Application
import by.grsu.movieexplorer.movies.data.mapper.movieMapperModule
import by.grsu.movieexplorer.movies.repository.movieDetailRepositoryModule
import by.grsu.movieexplorer.movies.repository.moviesRepositoryModule
import by.grsu.movieexplorer.movies.viewmodel.movieDetailViewModelModule
import by.grsu.movieexplorer.movies.viewmodel.movieViewModelModule
import by.grsu.movieexplorer.retrofit.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieExplorerApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieExplorerApp)

            modules(
                networkModule,
                movieDetailRepositoryModule,
                moviesRepositoryModule,
                movieMapperModule,
                movieViewModelModule,
                movieDetailViewModelModule
            )
        }
    }
}