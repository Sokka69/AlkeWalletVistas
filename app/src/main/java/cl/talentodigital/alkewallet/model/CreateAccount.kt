package cl.talentodigital.alkewallet.model

data class CreateAccount(
    val creationDate: String,
    val money: Long,
    val isBlocked: Boolean,
    val userID: Long
)
