package sofascore.academy.sofaproject.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import sofascore.academy.sofaproject.view.fragments.NewFighterFragment
import sofascore.academy.sofaproject.view.fragments.ViewFightersFragment

class SectionsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return NewFighterFragment()
            1 -> return ViewFightersFragment()
        }
        return NewFighterFragment()
    }
}
