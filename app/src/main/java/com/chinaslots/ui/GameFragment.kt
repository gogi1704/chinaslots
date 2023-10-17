package com.chinaslots.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chinaslots.ui.adapter.gameAdapter.GameAdapter
import com.chinaslots.ui.adapter.gameAdapter.GameListener
import com.chinaslots.viewModels.BonusViewModel
import com.chinaslots.viewModels.GameViewModel
import com.chinaslots.viewModels.LevelViewModel
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.FragmentGameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private val gameViewModel: GameViewModel by viewModels()
    private val levelViewModel: LevelViewModel by viewModels()
    private val bonusViewModel: BonusViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        val diff = requireArguments().getString("SKILL")
        gameViewModel.useDif(diff ?: "LOW")
        val id = requireArguments().getInt("ID")

        binding.textSkill.text = if (diff == "LOW") "4 out of 5" else "5 out of 5"

        gameViewModel.attempLiveData.observe(viewLifecycleOwner) {
            binding.textCount.text = it.toString()
        }

        gameViewModel.isWinOrLoseLiveData.observe(viewLifecycleOwner) {
            with(binding) {
                if (it == "WIN") {
                    winBack.visibility = View.VISIBLE
                    win.visibility = View.VISIBLE
                    buttonHome.visibility = View.VISIBLE
                    levelViewModel.openLevel(id + 1)
                    swap.visibility = View.GONE
                    bonusViewModel.win()

                } else if (it == "LOSE") {
                    winBack.visibility = View.VISIBLE
                    lose.visibility = View.VISIBLE
                    buttonHome.visibility = View.VISIBLE
                    swap.visibility = View.GONE

                }
            }
        }

        gameViewModel.listLiveData.observe(viewLifecycleOwner) {
            val adapter = GameAdapter(requireContext(), it.toList(), object : GameListener {
                override fun click(id: Int) {
                    gameViewModel.clickItem(id)
                }

            })
            binding.gridGame.adapter = adapter
        }

        binding.swapButton.setOnClickListener {
            gameViewModel.swap()
        }

        binding.buttonHome.setOnClickListener {
            with(binding) {
                winBack.visibility = View.GONE
                win.visibility = View.GONE
                buttonHome.visibility = View.GONE
                lose.visibility = View.GONE
                swap.visibility = View.VISIBLE

                findNavController().navigate(R.id.levelFragment)


            }

        }




        return binding.root
    }

}