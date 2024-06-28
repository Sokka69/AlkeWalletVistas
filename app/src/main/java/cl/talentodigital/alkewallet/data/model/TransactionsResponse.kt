package cl.talentodigital.alkewallet.data.model

data class TransactionsResponse(

    val previousPage: Any?,
    val nextPage: Any?,
    val data: List<Transaction>?,
    val error: String?,
    val status: String?
)
