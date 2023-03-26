package sofascore.academy.sofaproject.data

import android.content.Context
import sofascore.academy.sofaproject.R
import java.net.URL

data class Coach(
    val firstName: String,
    val lastName: String,
    val speciality: String,
    val imageUrl: URL

) : java.io.Serializable {
    override fun toString(): String {
        return "$firstName, $lastName, $speciality"
    }

    fun getDataAsList(context: Context) = arrayOf(
        context.resources.getString(R.string.fighter_name, firstName, lastName),
        speciality
    )
}
