package com.chinaslots.ui.adapter.bonusAdapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.chinaslots.models.BonusModel
import com.fortunemouses.chinaslots.R

interface BonusListener {
    fun click(id: Int)
}

class BonusAdapter(
    private val context: Context,
    private val list: List<BonusModel>,
    private val listener: BonusListener
) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val image: ImageView = if (convertView == null) {
            ImageView(context)
        } else {
            convertView as ImageView
        }
        image.setOnClickListener {
            listener.click(list[position].id)
        }
        image.setImageResource(R.drawable.ic_coin)
        return image
    }
}