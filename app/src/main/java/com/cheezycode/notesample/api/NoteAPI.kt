package com.cheezycode.notesample.api

import com.cheezycode.notesample.database.ApiProductModel
import com.cheezycode.notesample.utils.NetworkResult
import retrofit2.Response
import retrofit2.http.*

interface NoteAPI {

    @GET("products")
    suspend fun getNotes(): Response<List<ApiProductModel>>


}