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
import java.io.IOException

class RequestMoneyViewModel(application: Application) : AndroidViewModel(application) {

   val topUpResultLiveData = MutableLiveData<PaymentResponse?>()
   val errorMessageLiveData = MutableLiveData<String>()

    fun requestMoney(accountId: Int, concept: String, amount: Double) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccess
                if (token.isNullOrEmpty()) {
                    errorMessageLiveData.postValue("Token no encontrado")
                    return@launch
                }
                val paymentRequest = PaymentRequest(type = "topup", concept = concept, amount = amount)
                val response = ApiClient.apiService.sendPayment("Bearer $token", accountId, paymentRequest)
                if (response.isSuccessful) {
                    val paymentResponse = response.body()
                    topUpResultLiveData.postValue(paymentResponse)
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.e("RequestMoneyViewModel", "Error al realizar el depósito: $errorBody")
                    errorMessageLiveData.postValue("Error al realizar el depósito: $errorBody")
                }
            } catch (e: IOException) {
                Log.e("RequestMoneyViewModel", "Error de red: ${e.message}")
                errorMessageLiveData.postValue("Error de red: ${e.message}")

            } catch (e: Exception) {
                Log.e("RequestMoneyViewModel", "Error desconocido: ${e.message}")
                errorMessageLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }
}