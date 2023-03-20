package sofascore.academy.sofaproject.data

import android.content.Context
import sofascore.academy.sofaproject.R

enum class Stance(val stanceNameIndex: Int) {
    ORTHODOX(0), SOUTHPAW(1), SWITCH_STANCE(2);

    companion object {
        fun fromString(context: Context, stance: String): Stance? {
            return values().find {
                it.stanceNameIndex == context.resources.getStringArray(R.array.stances)
                    .indexOf(stance)
            }
        }
    }

    fun toString(context: Context): String {
        return context.resources.getStringArray(R.array.stances)[stanceNameIndex].toString()
    }
}
