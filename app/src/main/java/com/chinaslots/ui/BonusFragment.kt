package com.chinaslots.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chinaslots.ui.adapter.bonusAdapter.BonusAdapter
import com.chinaslots.ui.adapter.bonusAdapter.BonusListener
import com.chinaslots.ui.adapter.bonusAdapter.BonusTextAdapter
import com.chinaslots.viewModels.BonusViewModel
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.FragmentBonusBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BonusFragment : Fragment() {
    private lateinit var binding: FragmentBonusBinding
    private val viewModelBonus: BonusViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBonusBinding.inflate(layoutInflater, container, false)


        with(binding) {
            buttonTake.setOnClickListener {
                viewModelBonus.collectMoney()
                findNavController().navigate(R.id.action_bonusFragment_to_mainFragment)
            }
        }

        viewModelBonus.bonusesLiveData.observe(viewLifecycleOwner) {
            val adapter = BonusAdapter(
                requireContext(),
                it,
                object : BonusListener {
                    override fun click(id: Int) {
                        viewModelBonus.clickItem(id)
                        binding.grid.adapter =
                            BonusTextAdapter(requireContext(), viewModelBonus.data)
                    }
                }
            )
            binding.grid.adapter = adapter

        }







        return binding.root
    }


}

const val PREF_DAY = "PREF_DAY"