package by.grsu.movieexplorer.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migrations {
    companion object {
        val migration_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                with(database) {
                    execSQL("ALTER TABLE Movie ADD COLUMN overview TEXT")
                    execSQL("ALTER TABLE Movie ADD COLUMN trailerLink TEXT")
                }
            }
        }
    }
}