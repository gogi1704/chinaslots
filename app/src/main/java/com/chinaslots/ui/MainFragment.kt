package com.chinaslots.ui

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chinaslots.viewModels.MoneyViewModel
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MoneyViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
              findNavController().navigate(R.id.mainFragment)
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)

        with(binding) {


            buttonShop.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_shopFragment)
            }
            buttonStart.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_levelFragment)
            }
            info.setOnClickListener {
                findNavController().navigate(R.id.action_mainFragment_to_guideFragment)
            }

        }
        viewModel.moneyLiveData.observe(viewLifecycleOwner) {
            binding.moneyCount.text = it.toString()
        }




        return binding.root
    }


}