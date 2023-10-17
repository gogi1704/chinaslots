package com.chinaslots.ui.adapter.levelAdapter

import androidx.recyclerview.widget.DiffUtil
import com.chinaslots.models.LevelModel

class LevelCallBack : DiffUtil.ItemCallback<LevelModel>() {
    override fun areItemsTheSame(oldItem: LevelModel, newItem: LevelModel): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: LevelModel, newItem: LevelModel): Boolean =
        oldItem == newItem
}