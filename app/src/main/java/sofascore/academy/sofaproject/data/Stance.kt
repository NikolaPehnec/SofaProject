package sofascore.academy.sofaproject.data

enum class Stance(val stanceName: String) {
    ORTHODOX("Orthodox"), SOUTHPAW("Southpaw"), SWITCH_STANCE("Switch stance");

    companion object {
        fun fromString(stance: String): Stance? {
            return values().find { it.stanceName == stance }
        }
    }
}