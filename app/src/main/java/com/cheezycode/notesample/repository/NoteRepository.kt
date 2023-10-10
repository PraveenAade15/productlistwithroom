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
    private val _dataLiveData = MediatorLiveData<NetworkResult<List<AllDataModel>>>()
    val dataLiveData: LiveData<NetworkResult<List<AllDataModel>>> = _dataLiveData

    init {
        _dataLiveData.addSource(localDataSource.allNotesLiveData) {
            _dataLiveData.postValue(NetworkResult.Success(it))
        }
    }

    suspend fun fetchData(): NetworkResult<List<AllDataModel>> {
        if (context.isNetworkAvailable()) {
            try {
                val response = noteAPI.getNotes()
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val allDataList = mapApiProductsToAllDataModels(responseBody)
                        localDataSource.insertRecipes(allDataList)
                        _dataLiveData.postValue(NetworkResult.Success(allDataList)) // Update LiveData with API data
                        return NetworkResult.Success(allDataList)
                    }
                }
                return NetworkResult.Error("API response not successful")
            } catch (e: Exception) {
                return NetworkResult.Error(e.message)
            }
        } else {
            return NetworkResult.Error("Internet is Not There")
        }

        // If the user is offline or the API call failed, fetch data from Room
        val localData = localDataSource.allNotesLiveData.value
        if (!localData.isNullOrEmpty()) {
            // Return Room data if available
            return NetworkResult.Success(localData)
        } else {
            // Handle the case where there is no data in Room as well
            // You can throw an exception or return an empty list here
            return NetworkResult.Error("No data available")
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






