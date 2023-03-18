package sofascore.academy.sofaproject.data

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
    override fun toString(): String {
        return "$firstName, $lastName, $nickname, $height, $weight, $reach, ${stance.stanceName}," +
            " ${fightingStyle.styleName}, $win, $lose, $draw"
    }
}
