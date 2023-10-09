package com.cheezycode.notesample.utils

sealed class State<T> {
    data class Success<T>(val data: T) : State<T>()
    data class Error<T>(val message: String) : State<T>()
    object Loading : State<Nothing>()
}
//sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
//
//    class Success<T>(data: T) : NetworkResult<T>(data)
//    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
//    class Loading<T> : NetworkResult<T>()
//
//}
