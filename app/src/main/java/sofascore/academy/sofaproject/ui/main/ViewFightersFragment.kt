package sofascore.academy.sofaproject.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.adapters.FighterRecyclerAdapter
import sofascore.academy.sofaproject.data.Coach
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.databinding.FragmentViewFightersBinding

class ViewFightersFragment : Fragment(), FighterRecyclerAdapter.OnItemClickListener, MenuProvider {
    private val peopleViewModel: FighterViewModel by activityViewModels()
    private var _binding: FragmentViewFightersBinding? = null
    private val binding get() = _binding!!
    private lateinit var fighterArrayAdapter: FighterRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fighterArrayAdapter =
            FighterRecyclerAdapter(requireContext(), mutableListOf(), this)

        peopleViewModel.fighterList.observe(this) {
            it?.let {
                fighterArrayAdapter.addItems(it)
                checkEmptyAdapter()
            }
        }

        peopleViewModel.coachesList.observe(this) {
            it?.let {
                fighterArrayAdapter.addItems(it)
                checkEmptyAdapter()
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

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    override fun onItemClick(item: Any) {
        val intent = when (item) {
            is Fighter -> {
                Intent(requireContext(), FighterDetailActivity::class.java).apply {
                    putExtra(getString(R.string.fighter_key), item)
                }
            }
            is Coach -> {
                Intent(requireContext(), CoachDetailActivity::class.java).apply {
                    putExtra(getString(R.string.coach_key), item)
                }
            }
            else -> null
        }

        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_fighters, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.hide_data_action -> {
                if (menuItem.title!! == getString(R.string.action_hide_data)) {
                    fighterArrayAdapter.removeAllItems()
                    checkEmptyAdapter()
                    menuItem.title = getString(R.string.action_show_data)
                } else {
                    peopleViewModel.setDefaultFighters()
                    peopleViewModel.setDefaultCoaches()
                    menuItem.title = getString(R.string.action_hide_data)
                }
                true
            }
            else -> false
        }
    }

    private fun checkEmptyAdapter() {
        if (fighterArrayAdapter.getNumberOfItems() == 0) {
            binding.noDataAnimation.playAnimation()
        } else {
            binding.noDataAnimation.progress = 0f
            binding.noDataAnimation.pauseAnimation()
        }
    }
}
