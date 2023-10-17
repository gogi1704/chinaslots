package com.chinaslots.ui.adapter.guideAdapter

import androidx.recyclerview.widget.DiffUtil
import com.chinaslots.models.LevelModel

class GuideCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean =
       false

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean =
        oldItem == newItem
}