package sofascore.academy.sofaproject.data

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
}
