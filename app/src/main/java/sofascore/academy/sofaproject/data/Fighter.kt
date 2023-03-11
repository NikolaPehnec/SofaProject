package sofascore.academy.sofaproject.data

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
    val draw: String

) {
    override fun toString(): String {
        return "$firstName, $lastName, $nickname, $height, $weight, $reach, ${stance.stanceName}," +
                " ${fightingStyle.styleName}, $win, $lose, $draw"
    }
}
