package cl.talentodigital.alkewallet.data.model

data class UserResponse(
    val id: Long,
    val first_name: String,
    val last_name: String,
    val email: String,
    val password: String,
    val roleID: Long = 1,
    val points: Long = 0
)
