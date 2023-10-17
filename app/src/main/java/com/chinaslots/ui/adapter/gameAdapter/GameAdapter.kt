package com.chinaslots.ui.adapter.gameAdapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.chinaslots.models.BonusModel
import com.chinaslots.models.GameModel
import com.fortunemouses.chinaslots.R

interface GameListener {
    fun click(id: Int)
}

class GameAdapter(
    private val context: Context,
    private val list: List<GameModel>,
    private val listener: GameListener
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
        var view: View
        if (convertView == null) {
            view = View(context)

            val inflaiter = LayoutInflater.from(context)
            view = inflaiter.inflate(R.layout.game_item_layout, parent, false)
        } else {
            view = convertView
        }
        if (list[position].isChecked) {
            view.findViewById<ImageView>(R.id.image).colorFilter =
                PorterDuffColorFilter(Color.CYAN, PorterDuff.Mode.MULTIPLY)
        } else {
            view.findViewById<ImageView>(R.id.image).clearColorFilter()
        }
        when (list[position].image) {
            1 -> {
                view.findViewById<ImageView>(R.id.image).setImageResource(R.drawable.ic_item1)
            }

            2 -> {
                view.findViewById<ImageView>(R.id.image).setImageResource(R.drawable.ic_item2)

            }

            3 -> {
                view.findViewById<ImageView>(R.id.image).setImageResource(R.drawable.ic_item3)
            }

            4 -> {
                view.findViewById<ImageView>(R.id.image).setImageResource(R.drawable.ic_item4)
            }

            else -> {
                view.findViewById<ImageView>(R.id.image).setImageResource(R.drawable.ic_item5)
            }
        }

        view.setOnClickListener {
            listener.click(list[position].id)
        }

        return view
    }
}