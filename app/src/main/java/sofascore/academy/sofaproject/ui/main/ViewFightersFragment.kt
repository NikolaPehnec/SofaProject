package sofascore.academy.sofaproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.databinding.FragmentViewFightersBinding
import sofascore.academy.sofaproject.utils.dpToPx
import kotlin.math.roundToInt

class ViewFightersFragment : Fragment() {
    private val peopleViewModel: FighterViewModel by activityViewModels()
    private var _binding: FragmentViewFightersBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        peopleViewModel.fighterList.observe(this) {
            it?.let {
                populateData(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewFightersBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun populateData(contacts: MutableList<Fighter>) {
        binding.fightersLinearLayout.removeAllViews()

        for (contact in contacts) {
            val newTextView = TextView(requireContext())
            newTextView.text = contact.toString()

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            val marginTop: Int = resources.getDimension(R.dimen.margin_top_textview).roundToInt()
            layoutParams.setMargins(0, marginTop.dpToPx(), 0, 0)
            newTextView.layoutParams = layoutParams

            binding.fightersLinearLayout.addView(newTextView)
        }
    }
}
