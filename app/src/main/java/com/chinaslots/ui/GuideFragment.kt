package com.chinaslots.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chinaslots.ui.adapter.guideAdapter.GuideAdapter
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.FragmentGuideBinding


class GuideFragment : Fragment() {
    private lateinit var binding: FragmentGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGuideBinding.inflate(layoutInflater, container, false)

        val adapter = GuideAdapter()

        val recycler = binding.recycler
        recycler.adapter = adapter
        adapter.submitList(listOf(R.drawable.guide1 , R.drawable.guide3 , R.drawable.guide2))
        return binding.root
    }


}