package sofascore.academy.sofaproject.data

enum class FightingStyle(val styleName: String) {
    STRIKER("Striker"), GRAPPLER("Grappler"), ALL_ROUNDER("All rounder");

    companion object {
        fun fromString(style: String): FightingStyle? {
            return values().find { it.styleName == style }
        }
    }
}
