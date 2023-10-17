package com.chinaslots.ui.adapter.guideAdapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chinaslots.models.LevelModel
import com.chinaslots.ui.adapter.levelAdapter.LevelAdapter
import com.chinaslots.ui.adapter.levelAdapter.LevelCallBack
import com.chinaslots.ui.adapter.levelAdapter.LevelClickListener
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.GuideItemBinding
import com.fortunemouses.chinaslots.databinding.LevelItemLayoutBinding

class GuideAdapter() :
    ListAdapter<Int, GuideAdapter.GuideViewHolder>(GuideCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuideViewHolder {
        val binding =
            GuideItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuideViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GuideViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class GuideViewHolder(
        private val binding: GuideItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Int) {
            binding.image.setImageResource(item)
        }
    }

}