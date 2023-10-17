package com.chinaslots.ui.adapter.bonusAdapter

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.chinaslots.models.BonusModel
import com.fortunemouses.chinaslots.R


class BonusTextAdapter(
    private val context: Context,
    private val list: List<BonusModel>,
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
        image.minimumWidth = 300
        image.minimumHeight =300
        when (list[position].countBonus) {
            100 -> {
                image.setImageResource(R.drawable.text_oneth)
                if (list[position].isWin){
                    image.drawable.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY );
                }
            }

            500 -> {
                image.setImageResource(R.drawable.text_fifth)
                if (list[position].isWin){
                    image.drawable.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY );
                }
            }

            1000 -> {
                image.setImageResource(R.drawable.text_one)
                if (list[position].isWin){
                    image.drawable.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY );
                }
            }

            2000 -> {
                image.setImageResource(R.drawable.text_two)
                if (list[position].isWin){
                    image.drawable.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY );
                }
            }

            5000 -> {
                image.setImageResource(R.drawable.text_five)
                if (list[position].isWin){
                    image.drawable.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY );
                }
            }
        }


        return image
    }
}