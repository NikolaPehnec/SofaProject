package sofascore.academy.sofaproject.view.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.ImageRequest
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Coach
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.databinding.ActivityFighterDetailBinding
import sofascore.academy.sofaproject.view.adapters.DetailRecyclerAdapter

class FighterDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFighterDetailBinding

    var fighter: Fighter? = null
    var coach: Coach? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFighterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        fighter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                getString(R.string.fighter_key),
                Fighter::class.java
            )
        } else {
            intent.getSerializableExtra(getString(R.string.fighter_key)) as Fighter?
        }

        coach = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                getString(R.string.coach_key),
                Coach::class.java
            )
        } else {
            intent.getSerializableExtra(getString(R.string.coach_key)) as Coach?
        }

        fighter?.let {
            fillFighterData()
        }
        coach?.let {
            fillCoachData()
        }
    }

    private fun fillFighterData() {
        binding.collapsingToolbarLayout.title =
            getString(R.string.fighter_name, fighter?.firstName, fighter?.lastName)

        val imgRequest = ImageRequest.Builder(this)
            .data(fighter?.imageUrl.toString())
            .placeholder(R.drawable.person_placeholder)
            .error(R.drawable.person_placeholder)
            .target(binding.expandedImage)
            .build()
        ImageLoader.Builder(this).build().enqueue(imgRequest)

        val fighterDataAdapter = DetailRecyclerAdapter(
            arrayOf(
                getString(R.string.name_label),
                getString(R.string.nickname_label),
                getString(R.string.height_label),
                getString(R.string.weight_label),
                getString(R.string.reach_label),
                getString(R.string.stance_label),
                getString(R.string.fighting_style_label),
                getString(R.string.win_draw_lose_label)
            ), fighter!!.getDataAsList(this)
        )

        binding.fighterInfo.fighterInfoRecyclerView.adapter = fighterDataAdapter
    }

    private fun fillCoachData() {
        binding.collapsingToolbarLayout.title =
            getString(R.string.fighter_name, coach?.firstName, coach?.lastName)

        val imgRequest = ImageRequest.Builder(this)
            .data(coach?.imageUrl.toString())
            .placeholder(R.drawable.person_placeholder)
            .error(R.drawable.person_placeholder)
            .target(binding.expandedImage)
            .build()
        ImageLoader.Builder(this).build().enqueue(imgRequest)

        val coachDataAdapter = DetailRecyclerAdapter(
            arrayOf(
                getString(R.string.name_label),
                getString(R.string.coach_speciality_label),
            ), coach!!.getDataAsList(this)
        )

        binding.fighterInfo.fighterInfoRecyclerView.adapter = coachDataAdapter
    }
}
