package sanjarbek.uz.currencycalculation.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import sanjarbek.uz.currencycalculation.R
import sanjarbek.uz.currencycalculation.utils.extentions.color

abstract class BaseFragment(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackgroundColorToScreen(view)
        setup()
        clicks()
        observe()
    }

    abstract fun setup()
    open fun setup(view: View) {}
    open fun clicks() {}
    open fun observe() {}
    open fun setBackgroundColorToScreen(view: View, color: Int = R.color.white) {
        view.setBackgroundColor(color(color))
    }
}