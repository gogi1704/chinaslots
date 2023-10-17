package com.chinaslots.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
@AndroidEntryPoint

class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        val dayPref = requireActivity().getSharedPreferences(PREF_DAY, Context.MODE_PRIVATE)
        val calendar = Calendar.getInstance()
        val today = calendar.get(Calendar.DAY_OF_MONTH)

        if (today != dayPref.getInt(PREF_DAY, -1)) {
            dayPref.edit()
                .putInt(PREF_DAY, today)
                .apply()
            findNavController().navigate(R.id.action_splashFragment_to_bonusFragment)
        } else findNavController().navigate(R.id.mainFragment)


        return binding.root
    }



}

