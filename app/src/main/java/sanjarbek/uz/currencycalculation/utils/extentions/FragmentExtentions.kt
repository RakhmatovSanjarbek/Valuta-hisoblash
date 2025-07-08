package sanjarbek.uz.currencycalculation.utils.extentions

import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment

fun Fragment.color(@ColorRes colorRes: Int) = requireContext().color(colorRes)