package cl.talentodigital.alkewallet.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.data.model.PaymentRequest
import cl.talentodigital.alkewallet.data.model.PaymentResponse
import cl.talentodigital.alkewallet.data.network.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SendMoneyViewModel(application: Application) : AndroidViewModel(application) {

    val paymentResultLiveData = MutableLiveData<PaymentResponse?>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun sendPayment(concept: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccess
                if (token.isNullOrEmpty()) {
                    errorMessageLiveData.postValue("Token no encontrado")
                    return@launch
                }
                // Obtener la fecha actual en el formato requerido
                val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                val currentDate = sdf.format(Date())

                val paymentRequest = PaymentRequest(type = "payment", concept = concept, amount = amount, date = currentDate )
                val response = ApiClient.apiService.sendPayment("Bearer $token", 2163,paymentRequest)

                if (response.isSuccessful) {
                    val paymentResponse = response.body()
                    paymentResultLiveData.postValue(paymentResponse)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("SendMoneyViewModel", "Error al enviar el pago: $errorBody")
                    errorMessageLiveData.postValue("Error al enviar el pago: $errorBody")
                }
            } catch (e: IOException) {
                Log.e("SendMoneyViewModel", "Error de red: ${e.message}")
                errorMessageLiveData.postValue("Error de red: ${e.message}")

            } catch (e: Exception) {
                Log.e("SendMoneyViewModel", "Error desconocido: ${e.message}")
                errorMessageLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }
}