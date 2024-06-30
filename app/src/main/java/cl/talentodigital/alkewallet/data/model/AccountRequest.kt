package cl.talentodigital.alkewallet.data.model

data class AccountRequest(
    val creationDate : String,
    val money : Double,
    val isBlocked : Boolean,
    val userId : Int
)
