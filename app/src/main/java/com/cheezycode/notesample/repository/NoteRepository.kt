package com.cheezycode.notesample.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.cheezycode.notesample.api.NoteAPI
import com.cheezycode.notesample.database.AllDataModel
import com.cheezycode.notesample.database.ApiProductModel

import com.cheezycode.notesample.database.LocalDataSource
import com.cheezycode.notesample.models.NoteResponse
import com.cheezycode.notesample.utils.Constants.isNetworkAvailable
import com.cheezycode.notesample.utils.NetworkResult
import javax.inject.Inject
import javax.inject.Singleton
class NoteRepository @Inject constructor(
    private val noteAPI: NoteAPI,
    private val localDataSource: LocalDataSource,
    private val context: Context
) {
    private val _dataLiveData = MediatorLiveData<List<AllDataModel>>()
    val dataLiveData: LiveData<List<AllDataModel>> = _dataLiveData

    init {
        _dataLiveData.addSource(localDataSource.allNotesLiveData) {
            _dataLiveData.value = it
        }
    }

    suspend fun fetchData(): List<AllDataModel> {
        if (context.isNetworkAvailable()) {
            Toast.makeText(context, "Internet is There", Toast.LENGTH_SHORT).show()
            try {
                val response = noteAPI.getNotes()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val allDataList = mapApiProductsToAllDataModels(responseBody)
                        localDataSource.insertRecipes(allDataList)
                        _dataLiveData.postValue(allDataList) // Update LiveData with API data
                        return allDataList
                    }
                }
                throw Exception("API response not successful")
            } catch (e: Exception) {
                // If there's an exception with the API call, log it or handle it as needed
                // Don't throw an exception here to avoid bypassing Room data retrieval
            }
        } else {
            Toast.makeText(context, "Internet is Not There", Toast.LENGTH_SHORT).show()
        }

        // If the user is offline or the API call failed, fetch data from Room
        val localData = localDataSource.allNotesLiveData.value
        if (!localData.isNullOrEmpty()) {
            // Return Room data if available
            Log.d("TAG", "fetchData: $localData")
            return localData
        } else {
            // Handle the case where there is no data in Room as well
            // You can throw an exception or return an empty list here
            throw Exception("No data available")
        }
    }

    private fun mapApiProductsToAllDataModels(apiProductList: List<ApiProductModel>): List<AllDataModel> {
        return apiProductList.map { apiProduct ->
            AllDataModel(
                title = apiProduct.title,
                price = apiProduct.price,
                description = apiProduct.description,
                category = apiProduct.category,
                image = apiProduct.image,
                ratingRate = apiProduct.rating?.rate,
                ratingCount = apiProduct.rating?.count
            )
        }
    }
}




