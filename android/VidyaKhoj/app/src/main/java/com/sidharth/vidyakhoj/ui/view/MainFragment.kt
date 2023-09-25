package com.sidharth.vidyakhoj.ui.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.sidharth.vidyakhoj.databinding.FragmentMainBinding
import com.sidharth.vidyakhoj.service.RefreshDataService.Companion.ACTION_REFRESH
import com.sidharth.vidyakhoj.ui.adapter.UniversityAdapter
import com.sidharth.vidyakhoj.ui.callback.OnWebsiteClickCallback
import com.sidharth.vidyakhoj.ui.viewmodel.UniversityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment(), OnWebsiteClickCallback {
    private val universityViewModel: UniversityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalBroadcastManager.getInstance(requireActivity()).registerReceiver(
            messageReceiver, IntentFilter(ACTION_REFRESH)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                universityViewModel.universities.collect {
                    binding.rvUniversity.adapter = UniversityAdapter(
                        universities = it,
                        onWebsiteClickCallback = this@MainFragment,
                    )
                }
            }
        }

        return binding.root
    }

    override fun onWebsiteClick(url: String) {
        val action = MainFragmentDirections.actionMainFragmentToWebViewFragment(url)
        findNavController().navigate(action)
    }

    private val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            universityViewModel.fetchUniversities()
        }
    }
}