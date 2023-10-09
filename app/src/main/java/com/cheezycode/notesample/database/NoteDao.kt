package com.cheezycode.notesample.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: List<AllDataModel>): List<Long>


    @Query("SELECT * FROM note_table")
    fun getAllNotes(): LiveData<List<AllDataModel>>

}
