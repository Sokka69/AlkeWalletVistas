package cl.talentodigital.alkewallet.data.model

data class LoginResponse(
    val accessToken: String?,
    val error: String?,
    val status: Int?
)
