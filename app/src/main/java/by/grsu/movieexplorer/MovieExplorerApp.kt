package by.grsu.movieexplorer

import android.app.Application
import by.grsu.movieexplorer.data.database.movieDatabaseModule
import by.grsu.movieexplorer.data.network.networkModule
import by.grsu.movieexplorer.data.repository.favouriteMovieRepositoryModule
import by.grsu.movieexplorer.data.repository.movieRepositoryModule
import by.grsu.movieexplorer.presentation.viewmodel.movieViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MovieExplorerApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MovieExplorerApp)
            modules(
                networkModule,

                movieRepositoryModule,
                favouriteMovieRepositoryModule,

                movieViewModelModule,

                movieDatabaseModule
            )
        }
    }
}