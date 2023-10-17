package com.chinaslots.ui.adapter.levelAdapter

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.chinaslots.models.LevelModel
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.LevelItemLayoutBinding

interface LevelClickListener {
    fun click(id: Int , isOpen:Boolean , skill:String)
}

class LevelAdapter(private val clickListener: LevelClickListener) :
    ListAdapter<LevelModel, LevelAdapter.LevelViewHolder>(LevelCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LevelViewHolder {
        val binding =
            LevelItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LevelViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: LevelViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class LevelViewHolder(
        private val binding: LevelItemLayoutBinding,
        private val clickListener: LevelClickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: LevelModel) {
            if (!item.isOpen) {
                binding.buttonLevel.colorFilter =
                    PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY)
            } else binding.buttonLevel.clearColorFilter()
            binding.buttonLevel.setOnClickListener {
                clickListener.click(item.id , item.isOpen , item.skill)
            }

            when(item.id){
                1->{
                    binding.buttonLevel.setImageResource(R.drawable.ic_level1)
                }
                2->{
                    binding.buttonLevel.setImageResource(R.drawable.ic_level2)

                }
                3->{
                    binding.buttonLevel.setImageResource(R.drawable.ic_level3)

                }
                4->{
                    binding.buttonLevel.setImageResource(R.drawable.ic_level4)

                }
                5->{
                    binding.buttonLevel.setImageResource(R.drawable.ic_level5)

                }
                else->{
                    binding.buttonLevel.setImageResource(R.drawable.ic_level6)

                }
            }
        }
    }

}