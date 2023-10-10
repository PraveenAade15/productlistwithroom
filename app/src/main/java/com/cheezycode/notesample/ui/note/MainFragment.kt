package com.cheezycode.notesample.ui.note

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.cheezycode.notesample.R
import com.cheezycode.notesample.databinding.FragmentMainBinding
import com.cheezycode.notesample.models.NoteResponse
import com.cheezycode.notesample.utils.Constants.TAG
import com.cheezycode.notesample.utils.NetworkResult
import com.cheezycode.notesample.utils.State
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel
//    private val noteViewModel by viewModels<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        viewModel.dataLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    // Handle success, update your UI with result.data
                    val data = result.data
                    Log.d(TAG, "onViewCreatedRoomdatabse: $data")
                    data?.forEach {
                        binding.textView.text = it.title.toString()
                    }
                    // ...
                }
                is NetworkResult.Error -> {

                    // Handle error, show an error message to the user
                    val errorMessage = result.message ?: "An error occurred"
                    // ...
                }
                is NetworkResult.Loading -> {
                    // Handle loading, e.g., show a loading spinner
                    // ...
                }
            }
        }

        // Fetch data when needed, e.g., in response to a button click or in onResume
        viewModel.fetchData()
    }
//        viewModel = ViewModelProvider(this)[NoteViewModel::class.java]

//        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
//
//        viewModel.dataLiveData.observe(viewLifecycleOwner) { data ->
//            // Update your UI with the data here
//            data.forEach {
//                binding.textView.text = it.title.toString()
//            }
//        }
//
//        // Fetch data when needed, e.g., in response to a button click or in onResume
//        viewModel.fetchData()

        // Observe the LiveData from the ViewModel
//        viewModel.dataLiveData.observe(viewLifecycleOwner, Observer { data ->
//            Log.d(TAG, "onViewCreatedRoomdataOffline: ${data.size}")
//            data.forEach {
//                binding.textView.text = it.title.toString()
//            }
//            // Update the UI with the data
//            // For example, update a RecyclerView adapter with the data
//        })
//        viewModel.allNotesLiveData.observe(viewLifecycleOwner,Observer{data ->
//            data.forEach {
//                binding.textView.text = it.title.toString()
//            }
//            Toast.makeText(requireContext(), "${data.size}", Toast.LENGTH_SHORT).show()
//        })
//        viewModel.fetchData()

//    }
}
