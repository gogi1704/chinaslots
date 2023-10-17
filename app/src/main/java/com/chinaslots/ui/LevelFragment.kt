package com.chinaslots.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chinaslots.models.LevelModel
import com.chinaslots.ui.adapter.levelAdapter.LevelAdapter
import com.chinaslots.ui.adapter.levelAdapter.LevelClickListener
import com.chinaslots.viewModels.LevelViewModel
import com.chinaslots.viewModels.MoneyViewModel
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.FragmentLevelBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LevelFragment : Fragment() {
    private lateinit var binding: FragmentLevelBinding
    private val levelViewModel: LevelViewModel by viewModels()
    private val viewModel: MoneyViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLevelBinding.inflate(layoutInflater, container, false)

        val adapter = LevelAdapter(object : LevelClickListener {
            override fun click(id: Int, isOpen: Boolean, skill: String) {
                if (isOpen) {
                    findNavController().navigate(
                        R.id.action_levelFragment_to_gameFragment,
                        Bundle().apply {
                            putString("SKILL", skill)
                            putInt("ID", id)
                        })
                } else {
                    Toast.makeText(requireContext(), "Level is closed! ", Toast.LENGTH_SHORT).show()
                }

            }

        })
        binding.levelRecycler.adapter = adapter

        with(binding){
            home.setOnClickListener {
                findNavController().navigate(R.id.mainFragment)
            }
            info.setOnClickListener {
                findNavController().navigate(R.id.guideFragment)
            }
        }


        levelViewModel.levelData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.moneyLiveData.observe(viewLifecycleOwner) {
            binding.moneyCount.text = it.toString()
        }

        return binding.root
    }


}