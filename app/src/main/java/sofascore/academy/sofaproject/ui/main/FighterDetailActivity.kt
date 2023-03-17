package sofascore.academy.sofaproject.ui.main

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.request.ImageRequest
import sofascore.academy.sofaproject.R
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.databinding.ActivityFighterDetailBinding

class FighterDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFighterDetailBinding

    var fighter: Fighter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFighterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        fighter = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra(
                getString(R.string.fighter_key), Fighter::class.java
            )
        } else {
            intent.getSerializableExtra(getString(R.string.fighter_key)) as Fighter?
        }

        fillFighterData()
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

        binding.fighterInfo.apply {
            fighterName.text =
                getString(R.string.fighter_name, fighter?.firstName, fighter?.lastName)
            nickname.text = fighter?.nickname
            height.text = fighter?.height
            weight.text = fighter?.weight
            reach.text = fighter?.reach
            stance.text = fighter?.stance?.stanceName
            winDrawLose.text =
                getString(R.string.fighter_score, fighter?.win, fighter?.draw, fighter?.lose)
        }
    }
}