import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import sanjarbek.uz.currencycalculation.R
import sanjarbek.uz.currencycalculation.domin.ui_model.CurrencyUIModel
import sanjarbek.uz.currencycalculation.ui.screen.exchange_rates.CurrenciesAdapter

class ItemSelectionDialogFragment(
    private val items: List<CurrencyUIModel>,
    private val onItemSelected: (String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var adapter: CurrenciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = CurrenciesAdapter { currency ->
            onItemSelected(currency)
            dismiss()
        }
        recyclerView.adapter = adapter
        adapter.submitList(items)
    }
}
