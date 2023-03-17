package sofascore.academy.sofaproject.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sofascore.academy.sofaproject.data.Fighter
import sofascore.academy.sofaproject.data.FightingStyle
import sofascore.academy.sofaproject.data.Stance
import java.net.URL

class FighterViewModel : ViewModel() {

    private val _fighterList: MutableLiveData<MutableList<Fighter>> = MutableLiveData()
    val fighterList: LiveData<MutableList<Fighter>> = _fighterList

    init {
        _fighterList.value = mutableListOf(
            Fighter(
                "Justin", "Gaethje", "The highlight", "180", "70.4", "177",
                Stance.ORTHODOX, FightingStyle.STRIKER, "23", "4", "0",
                URL(
                    "https://images2.minutemediacdn.com/image/upload/c_crop,w_1024,h_576,x_0,y_0/c_fill,w_720,ar_16:9,f_auto,q_auto,g_auto/images/voltaxMediaLibrary/mmsport/theduel_en_international_web/01gvk71hz508kjd696fm.jpg"
                )
            ), Fighter(
                "Israel", "Adesanya", "The Last Stylebender", "193", "84", "203",
                Stance.SWITCH_STANCE, FightingStyle.STRIKER, "23", "2", "0",
                URL(
                    "https://dmxg5wxfqgb4u.cloudfront.net/2021-03/68129%252Fprofile-galery%252Fprofile-picture%252FADESANYA_ISRAEL_BELT_03-06.png?null&itok=twxDO2ah"
                )
            ), Fighter(
                "Michael", "Chandler", "Iron", "173", "71", "180",
                Stance.ORTHODOX, FightingStyle.STRIKER, "23", "8", "0",
                URL(
                    "https://a.espncdn.com/combiner/i?img=/i/headshots/mma/players/full/2504988.png&w=350&h=254"
                )
            ), Fighter(
                "Charles", "Oliveira", "Do Bronx", "178", "70", "187",
                Stance.ORTHODOX, FightingStyle.GRAPPLER, "33", "9", "0",
                URL(
                    "https://a.espncdn.com/i/headshots/mma/players/full/2504169.png"
                )
            )
        )
    }

    fun addFighter(fighter: Fighter) {
        _fighterList.value?.add(fighter)
        _fighterList.value = _fighterList.value
    }
}
