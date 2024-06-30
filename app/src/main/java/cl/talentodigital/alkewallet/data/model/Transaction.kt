package cl.talentodigital.alkewallet.data.model

data class Transaction(
    val id: Int,
    val amount: String,
    val concept: String,
    val date: String,
    val type: String,
    val accountId: Int,
    val userId: Int,
    val toAccountId: Int,
    val toUserId: Int,
    val imgUrl: String?  // URL de la imagen relacionada con la transacción
)

