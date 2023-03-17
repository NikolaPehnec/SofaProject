package sofascore.academy.sofaproject.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.adapters.FighterRecyclerAdapter
import sofascore.academy.sofaproject.databinding.FragmentViewFightersBinding

class ViewFightersFragment : Fragment(), FighterRecyclerAdapter.OnItemClickListener {
    private val peopleViewModel: FighterViewModel by activityViewModels()
    private var _binding: FragmentViewFightersBinding? = null
    private val binding get() = _binding!!
    private lateinit var fighterArrayAdapter: FighterRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fighterArrayAdapter =
            FighterRecyclerAdapter(requireContext(), emptyList(), this)

        peopleViewModel.fighterList.observe(this) {
            it?.let {
                fighterArrayAdapter.setData(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewFightersBinding.inflate(inflater, container, false)

        activity?.title = getString(R.string.view_fighters_title)
        binding.fightersRecyclerView.adapter = fighterArrayAdapter

        return binding.root
    }

    override fun onItemClick(position: Int) {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
