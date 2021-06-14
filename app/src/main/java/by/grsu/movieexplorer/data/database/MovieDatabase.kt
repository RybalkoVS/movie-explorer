package by.grsu.movieexplorer.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.grsu.movieexplorer.data.dao.MovieDao
import by.grsu.movieexplorer.data.model.Movie
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val movieDatabaseModule = module {
    fun provideMovieDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "MovieDb")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMovieDao(movieDatabase: MovieDatabase): MovieDao {
        return movieDatabase.MovieDao()
    }

    single { provideMovieDatabase(androidApplication()) }
    single { provideMovieDao(get()) }
}

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao
}