package com.mcmouse88.hrenapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class FragmentB : Fragment(R.layout.fragment_b) {

    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val start = view.findViewById<Button>(R.id.btn_start)
        val back = view.findViewById<Button>(R.id.btn_back)

        start.setOnClickListener {
            viewModel.changeStr()
        }

        back.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        /*viewModel.title.observe(viewLifecycleOwner) {
            start.text = it
        }*/

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.title.collect {
                    start.text = it
                }
            }
        }
    }
}