package sofascore.academy.sofaproject.data

import android.content.Context
import sofascore.academy.sofaproject.R
import java.net.URL

data class Fighter(
    val firstName: String,
    val lastName: String,
    val nickname: String,
    val height: String,
    val weight: String,
    val reach: String,
    val stance: Stance,
    val fightingStyle: FightingStyle,
    val win: String,
    val lose: String,
    val draw: String,
    val imageUrl: URL

) : java.io.Serializable {

    fun getDataAsList(context: Context) = arrayOf(
        context.resources.getString(R.string.fighter_name, firstName, lastName),
        nickname,
        height,
        weight,
        reach,
        stance.toString(context),
        fightingStyle.toString(context),
        context.resources.getString(R.string.fighter_score, win, lose, draw)
    )
}
