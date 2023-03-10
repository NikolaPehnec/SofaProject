package sofascore.academy.sofaproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import sofascore.academy.sofaproject.databinding.ActivityBottomNavigationBinding

// testing
class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bottomNavigationView = binding.bottomNavigation
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        bottomNavigationView.setupWithNavController(navController)
    }
}
