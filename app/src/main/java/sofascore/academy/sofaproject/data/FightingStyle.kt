package sofascore.academy.sofaproject.data

import android.content.Context
import sofascore.academy.sofaproject.R

enum class FightingStyle(val styleNameIndex: Int) {
    STRIKER(0), GRAPPLER(1), ALL_ROUNDER(2);

    companion object {
        fun fromString(context: Context, style: String): FightingStyle? {
            return values().find {
                it.styleNameIndex == context.resources.getStringArray(R.array.fighting_style_arr)
                    .indexOf(style)
            }
        }
    }

    fun toString(context: Context): String {
        return context.resources.getStringArray(R.array.fighting_style_arr)[styleNameIndex].toString()
    }
}
