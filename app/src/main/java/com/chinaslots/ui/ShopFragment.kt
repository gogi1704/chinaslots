package com.chinaslots.ui

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.chinaslots.viewModels.MoneyViewModel
import com.fortunemouses.chinaslots.R
import com.fortunemouses.chinaslots.databinding.FragmentLevelBinding
import com.fortunemouses.chinaslots.databinding.FragmentShopBinding
import dagger.hilt.android.AndroidEntryPoint
import java.lang.StringBuilder

@AndroidEntryPoint
class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding
    private val moneyViewModel: MoneyViewModel by viewModels()
    private lateinit var listView: MutableList<ImageView>
    private lateinit var textView: MutableList<TextView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(layoutInflater, container, false)
        val shopPref = requireContext().getSharedPreferences(SHOP_PREF_NAME, Context.MODE_PRIVATE)
        var opens = shopPref.getString(SHOP_PREF_VALUE, "000000000") ?: "000000000"

        listView = mutableListOf(
            binding.shop1,
            binding.shop2,
            binding.shop3,
            binding.shop4,
            binding.shop5,
            binding.shop6,
            binding.shop7,
            binding.shop8,
            binding.shop9
        )
        textView = mutableListOf(
            binding.num1,
            binding.num2,
            binding.num3,
            binding.num4,
            binding.num5,
            binding.num6,
            binding.num7,
            binding.num8,
            binding.num9,
        )
        check(opens!!, listView, textView)


        with(binding) {
            moneyViewModel.moneyLiveData.observe(viewLifecycleOwner) {
                moneyCount.text = it.toString()
            }
            moneyViewModel.errorLiveData.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
                }
            }



            shop1.setOnClickListener {

                if (moneyCount.text.toString().toInt() >= num1.text.toString().toInt()) {
                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 0) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num1.text.toString().toInt())

                }


            }
            shop2.setOnClickListener {
                if (moneyCount.text.toString().toInt() >= num2.text.toString().toInt()) {

                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 1) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num2.text.toString().toInt())

                }
            }
            shop3.setOnClickListener {

                if (moneyCount.text.toString().toInt() >= num3.text.toString().toInt()) {

                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 2) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num3.text.toString().toInt())

                }
            }
            shop4.setOnClickListener {
                if (moneyCount.text.toString().toInt() >= num4.text.toString().toInt()) {
                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 3) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num4.text.toString().toInt())

                }
            }
            shop5.setOnClickListener {

                if (moneyCount.text.toString().toInt() >= num5.text.toString().toInt()) {
                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 4) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num5.text.toString().toInt())

                }
            }
            shop6.setOnClickListener {
                if (moneyCount.text.toString().toInt() >= num6.text.toString().toInt()) {
                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 5) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num6.text.toString().toInt())

                }
            }
            shop7.setOnClickListener {
                if (moneyCount.text.toString().toInt() >= num7.text.toString().toInt()) {
                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 6) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num7.text.toString().toInt())

                }
            }
            shop8.setOnClickListener {
                if (moneyCount.text.toString().toInt() >= num8.text.toString().toInt()) {
                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 7) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num8.text.toString().toInt())

                }
            }
            shop9.setOnClickListener {
                if (moneyCount.text.toString().toInt() >= num8.text.toString().toInt()) {
                    val o = StringBuilder()
                    for (i in opens!!.withIndex()) {
                        if (i.index != 8) {
                            o.append(i.value)
                        } else o.append(1)
                    }
                    opens = o.toString()
                    shopPref.edit()
                        .putString(SHOP_PREF_VALUE, o.toString())
                        .apply()
                    check(o.toString(), listView, textView)
                    moneyViewModel.buyItem(num9.text.toString().toInt())

                }
            }
        }






        return binding.root
    }

    fun check(string: String, listView: MutableList<ImageView>, textView: MutableList<TextView>) {
        for (i in listView.withIndex()) {
            if (string[i.index] == '0') {
                i.value.colorFilter =
                    PorterDuffColorFilter(Color.DKGRAY, PorterDuff.Mode.MULTIPLY)
            } else {
                i.value.clearColorFilter()
                textView[i.index].visibility = View.GONE
            }
        }
    }

    override fun onPause() {
        moneyViewModel.errorLiveData.value = false
        super.onPause()
    }


}


const val SHOP_PREF_NAME = "SHOP_PREF_NAME"
const val SHOP_PREF_VALUE = "SHOP_PREF_VALUE"