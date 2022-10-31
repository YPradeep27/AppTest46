package com.app.myapptest42.models.room

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.app.myapp3.models.roomDatabase.Images
import com.app.myapp3.models.roomDatabase.ImagesDao

@Database(entities = [Images::class] ,version = 1)
abstract class ImageDatabase : RoomDatabase() {

    abstract fun roomDao() : ImagesDao

    companion object {

        @Volatile
        private var INSTANCE: ImageDatabase? = null

        fun getDatabase(context: Application): ImageDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            if (INSTANCE == null) {
                synchronized(this) {
                    // Pass the database to the INSTANCE
                    INSTANCE = buildDatabase(context)
                }
            }
            // Return database.
            return INSTANCE!!
        }

        private fun buildDatabase(context: Context): ImageDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ImageDatabase::class.java,
                "rooms_database"
            )
                .build()
        }
    }
}