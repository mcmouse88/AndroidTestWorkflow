package com.mcmouse88.hrenapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@AndroidEntryPoint
class FragmentA : Fragment(R.layout.fragment_a) {

  //  private val viewModel by viewModels<MainViewModel>()

    // private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       // viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        val button = view.findViewById<Button>(R.id.btn_trans)
        button.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentB)
            // viewModel.changeStr()
        }

        /*lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.title.collect {
                    button.text = it
                }
            }
        }*/

        /*viewModel.title.observe(viewLifecycleOwner) {
            button.text = it
        }*/
    }
}