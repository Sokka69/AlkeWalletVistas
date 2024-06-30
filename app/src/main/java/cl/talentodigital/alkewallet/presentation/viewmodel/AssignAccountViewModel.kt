package cl.talentodigital.alkewallet.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.data.model.AccountRequest
import cl.talentodigital.alkewallet.data.network.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AssignAccountViewModel(application: Application) : AndroidViewModel(application) {

    val accountCreatedLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()

    fun assignAccountToUser(userId: Int) {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val currentDate = sdf.format(Date())

        val accountRequest = AccountRequest(
            creationDate = currentDate,
            money = 2000.0,
            isBlocked = false,
            userId = userId
        )

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccess
                if (token != null) {
                    errorMessageLiveData.postValue("Token no encontrado")
                    return@launch
                }

                val response = ApiClient.apiService.createAccount("Bearer $token", accountRequest)
                if (response.isSuccessful && response.body() != null) {
                    accountCreatedLiveData.postValue(true)
                } else {
                    accountCreatedLiveData.postValue(false)
                }
            } catch (e: IOException) {
                Log.e("AssignAccountVM", "Error de red: ${e.message}")
                errorMessageLiveData.postValue("Error de red: ${e.message}")
            } catch (e: Exception) {
                Log.e("AssignAccountVM", "Error desconocido: ${e.message}")
                errorMessageLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }
}