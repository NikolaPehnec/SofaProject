package sofascore.academy.sofaproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.databinding.FragmentViewFightersBinding

class ViewFightersFragment : Fragment() {
    private val peopleViewModel: FighterViewModel by activityViewModels()
    private var _binding: FragmentViewFightersBinding? = null
    private val binding get() = _binding!!
    private lateinit var fighterArrayAdapter: ArrayAdapter<Fighter>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fighterArrayAdapter =
            ArrayAdapter<Fighter>(requireContext(), android.R.layout.simple_list_item_1)

        peopleViewModel.fighterList.observe(this) {
            it?.let {
                fighterArrayAdapter.clear()
                fighterArrayAdapter.addAll(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewFightersBinding.inflate(inflater, container, false)

        binding.fightersListView.adapter = fighterArrayAdapter

        return binding.root
    }

}
