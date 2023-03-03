package sofascore.academy.sofaproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import sofascore.academy.sofaproject.databinding.ActivityTabbedBinding
import sofascore.academy.sofaproject.ui.main.SectionsPagerAdapter

class TabbedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabbedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabbedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager, lifecycle)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter

        val bottomNavigationView = binding.bottomNavigation
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.page_1 -> {
                    viewPager.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.page_2 -> {
                    viewPager.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener true
            }
        }

    }
}