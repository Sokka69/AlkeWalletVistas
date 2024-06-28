package cl.talentodigital.alkewallet.data.model

data class PaymentRequest(
    val type: String,
    val concept: String,
    val amount: Double
)
