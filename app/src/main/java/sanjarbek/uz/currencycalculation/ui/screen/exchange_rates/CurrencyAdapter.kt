package sanjarbek.uz.currencycalculation.ui.screen.exchange_rates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import sanjarbek.uz.currencycalculation.R
import sanjarbek.uz.currencycalculation.databinding.ItemCurrencyBinding
import sanjarbek.uz.currencycalculation.domin.ui_model.CurrencyUIModel

class CurrenciesAdapter(
    private val onItemClicked: (String) -> Unit,
) :
    ListAdapter<CurrencyUIModel, CurrenciesAdapter.CurrenciesViewHolder>(diffUtil) {

    inner class CurrenciesViewHolder(
        private val binding: ItemCurrencyBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(model: CurrencyUIModel) {
            with(binding) {
                tvCcy.text = model.currency
                tvCcyNm.text = model.currencyNameUz
                tvRate.text = "${model.rate} UZB"
                tvDiff.text = "${model.diff}%"
                if (model.diff.contains("-")) {
                    ivDiff.setImageResource(R.drawable.ic_decrease)
                } else {
                    ivDiff.setImageResource(R.drawable.ic_growth)
                }
                val str = model.currency.substring(0, 2).lowercase()
                ivFlag.load("https://flagcdn.com/w160/$str.png")
                root.setOnClickListener {
                    onItemClicked(model.currency)
                }
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CurrencyUIModel>() {
            override fun areItemsTheSame(
                oldItem: CurrencyUIModel,
                newItem: CurrencyUIModel,
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CurrencyUIModel,
                newItem: CurrencyUIModel,
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrenciesViewHolder {
        return CurrenciesViewHolder(
            ItemCurrencyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CurrenciesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
