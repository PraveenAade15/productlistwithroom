package com.cheezycode.notesample.database

import android.util.Log
import androidx.lifecycle.LiveData
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val noteDao: NoteDao
) {
   val allNotesLiveData: LiveData<List<AllDataModel>> = noteDao.getAllNotes()
    init {
        // Observe the LiveData and log the data when it changes
        allNotesLiveData.observeForever { allDataModels ->
            allDataModels?.let {
                Log.d("LocalDataSourceRoomdata", "Data Retrieved successfully. Result: $it")
            }
        }
    }
    suspend fun insertRecipes(recipesEntity: List<AllDataModel>) {
        try {
            val insertResult = noteDao.insert(recipesEntity)
            Log.d("LocalDataSource", "Data inserted successfully. Result: $insertResult")
        } catch (e: Exception) {
            // Handle the insertion error (e.g., log the error)
            Log.e("LocalDataSource", "Error inserting data into Room database: ${e.message}", e)
        }
    }
}