package sofascore.academy.sofaproject.view.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.ImageRequest
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Coach
import sofascore.academy.sofaproject.databinding.ActivityCoachDetailBinding

class CoachDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoachDetailBinding

    var coach: Coach? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoachDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        coach = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                getString(R.string.coach_key),
                Coach::class.java
            )
        } else {
            intent.getSerializableExtra(getString(R.string.coach_key)) as Coach?
        }

        fillFighterData()
    }

    private fun fillFighterData() {
        binding.collapsingToolbarLayout.title =
            getString(R.string.fighter_name, coach?.firstName, coach?.lastName)

        val imgRequest = ImageRequest.Builder(this)
            .data(coach?.imageUrl.toString())
            .placeholder(R.drawable.person_placeholder)
            .error(R.drawable.person_placeholder)
            .target(binding.expandedImage)
            .build()
        ImageLoader.Builder(this).build().enqueue(imgRequest)

        binding.coachInfo.apply {
            coachName.text = getString(R.string.fighter_name, coach?.firstName, coach?.lastName)
            coachSpeciality.text = coach?.speciality
        }
    }
}
