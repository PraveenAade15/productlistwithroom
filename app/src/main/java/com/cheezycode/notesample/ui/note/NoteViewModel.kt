package com.cheezycode.notesample.ui.note
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheezycode.notesample.database.AllDataModel

import com.cheezycode.notesample.repository.NoteRepository
import com.cheezycode.notesample.utils.NetworkResult
import com.cheezycode.notesample.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
    val dataLiveData: LiveData<NetworkResult<List<AllDataModel>>> = noteRepository.dataLiveData

    fun fetchData() {
        viewModelScope.launch {
            try {
                val result = noteRepository.fetchData()
                // The repository updates dataLiveData with the result
            } catch (e: Exception) {
                // Handle the exception, e.g., show an error message to the user
            }
        }
    }
}



//@HiltViewModel
//class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
//    val dataLiveData: LiveData<List<AllDataModel>> = noteRepository.dataLiveData
//
//    fun fetchData() {
//        viewModelScope.launch {
//            try {
//                noteRepository.fetchData()
//            } catch (e: Exception) {
//                // Handle the exception, e.g., show an error message to the user
//            }
//        }
//    }
//}


//    private val _dataStateLiveData = MutableLiveData<State<List<AllDataModel>>>()
//    val dataStateLiveData: LiveData<State<List<AllDataModel>>> = _dataStateLiveData
//
////    val dataStateLiveDataRoom: LiveData<State<List<AllDataModel>>> = noteRepository.dataLiveData.value
//
//    init {
//        fetchData()
//    }
//
//    private fun fetchData() {
//        viewModelScope.launch {
////            _dataStateLiveData.value = State.Loading
//            try {
//                val data = noteRepository.fetchData()
//                _dataStateLiveData.value = State.Success(data)
//            } catch (e: Exception) {
//                _dataStateLiveData.value = State.Error(e.message ?: "An error occurred")
//            }
//        }
//    }
//}

//@HiltViewModel
//class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {
//
//    val dataLiveData: LiveData<List<AllDataModel>> = noteRepository.dataLiveData
//
//    init {
//        // Fetch data when the ViewModel is created
//        viewModelScope.launch {
//            Log.d("fetchDataViewmodel", ": ${noteRepository.fetchData()} ")
//            noteRepository.fetchData()
//        }
//    }
//}

