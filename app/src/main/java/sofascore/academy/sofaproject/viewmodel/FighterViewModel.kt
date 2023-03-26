package sofascore.academy.sofaproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sofascore.academy.sofaproject.data.Coach
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.data.FightingStyle
import sofascore.academy.sofaproject.data.Stance
import java.net.URL

class FighterViewModel : ViewModel() {

    private val _fighterList: MutableLiveData<MutableList<Fighter>> = MutableLiveData()
    val fighterList: LiveData<MutableList<Fighter>> = _fighterList

    private val _coachesList: MutableLiveData<MutableList<Coach>> = MutableLiveData()
    val coachesList: LiveData<MutableList<Coach>> = _coachesList

    init {
        _fighterList.value = DefaultData.fighters
        _coachesList.value = DefaultData.coaches
    }

    fun addFighter(fighter: Fighter) {
        _fighterList.value?.add(fighter)
        _fighterList.value = _fighterList.value
    }

    fun setDefaultFighters() {
        _fighterList.value = DefaultData.fighters
    }

    fun setDefaultCoaches() {
        _coachesList.value = DefaultData.coaches
    }

    object DefaultData {
        val fighters = mutableListOf(
            Fighter(
                "Justin", "Gaethje", "The highlight", "180", "70.4", "177",
                Stance.ORTHODOX, FightingStyle.STRIKER, "23", "4", "0",
                URL(
                    "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/3022345.png"
                )
            ),
            Fighter(
                "Israel", "Adesanya", "The Last Stylebender", "193", "84", "203",
                Stance.SWITCH_STANCE, FightingStyle.STRIKER, "23", "2", "0",
                URL(
                    "https://dmxg5wxfqgb4u.cloudfront.net/2021-03/68129%252Fprofile-galery%252Fprofile-picture%252FADESANYA_ISRAEL_BELT_03-06.png?null&itok=twxDO2ah"
                )
            ),
            Fighter(
                "Michael", "Chandler", "Iron", "173", "71", "180",
                Stance.ORTHODOX, FightingStyle.STRIKER, "23", "8", "0",
                URL(
                    "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/2504988.png&w=350&h=254"
                )
            ),
            Fighter(
                "Charles", "Oliveira", "Do Bronx", "178", "70", "187",
                Stance.ORTHODOX, FightingStyle.GRAPPLER, "33", "9", "0",
                URL(
                    "https://a.espncdn.com/i/headshots/mma/players/full/2504169.png"
                )
            ),
            Fighter(
                "Max", "Holloway", "Blessed", "180", "66", "175",
                Stance.ORTHODOX, FightingStyle.ALL_ROUNDER, "23", "7", "0",
                URL(
                    "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/2614933.png&w=350&h=254"
                )
            )
        )

        val coaches = mutableListOf(
            Coach(
                "Renzo",
                "Gracie",
                "Grappling",
                URL("https://cdn.chatrisityodtong.com/wp-content/uploads/2020/07/Screenshot-2020-07-08-at-11.13.29-PM-e1594221726816.png")
            ),
            Coach(
                "Henri",
                "Hooft",
                "Striking",
                URL("https://cdn.shopify.com/s/files/1/1659/8997/files/henri_hooft_1.png")
            ),
            Coach(
                "Trevor",
                "Wittman",
                "Striking",
                URL("https://www.denverpost.com/wp-content/uploads/2016/05/1016boxer.jpg?w=320")
            ),
            Coach(
                "Greg",
                "Jackson",
                "All rounder",
                URL("https://cdn.shopify.com/s/files/1/1800/2299/articles/greg_jackson_1024x1024.jpg?v=1632907559")
            )
        )
    }
}
