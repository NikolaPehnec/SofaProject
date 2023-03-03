package sofascore.academy.sofaproject.data

data class Person(
    val firstName: String, val lastName: String, val age: String, val OIB: String,
    val gender: String, val education: String, val motherName: String, val fatherName: String,
    val nationality: String, val additionalInfo: String


) {
    override fun toString(): String {
        return "$firstName, $lastName, $age, $OIB, $gender, $education, $motherName, $fatherName, $nationality, $additionalInfo"
    }
}
