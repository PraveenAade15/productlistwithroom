package com.cheezycode.notesample.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [AllDataModel::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}


//@Database(entities = [AllDataModel::class], version = 1, exportSchema = false)
//abstract class NoteDatabase : RoomDatabase() {
//    abstract fun noteDao(): NoteDao
//
//    // Create a companion object to get a reference to the database.
//    companion object {
//        @Volatile
//        private var INSTANCE: NoteDatabase? = null
//
//        fun getDatabase(context: Context): NoteDatabase {
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    NoteDatabase::class.java,
//                    "note_database"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//}
