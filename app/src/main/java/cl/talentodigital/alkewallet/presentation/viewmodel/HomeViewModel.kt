package cl.talentodigital.alkewallet.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.data.network.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.IOException

class HomeViewModel (application: Application) : AndroidViewModel(application) {

    val accountCheckLiveData = MutableLiveData<Boolean>()
    val errorMessageLiveData = MutableLiveData<String>()
    val userBalanceLiveData = MutableLiveData<Double?>()

    fun checkUserAccount() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val token = AlkeWalletApp.tokenAccess
                if (token.isNullOrEmpty()) {
                    errorMessageLiveData.postValue("Token no encontrado")
                    return@launch
                }
                val response = ApiClient.apiService.getAccount("Bearer $token")
                if (response.isSuccessful) {
                    val accountData = response.body()
                    if (accountData != null && accountData.isNotEmpty()) {
                        AlkeWalletApp.userAccount = accountData[0]
                        accountCheckLiveData.postValue(true)
                        userBalanceLiveData.postValue(accountData[0].money)
                    } else {
                        accountCheckLiveData.postValue(false)
                    }
                } else {
                    accountCheckLiveData.postValue(false)
                }

            } catch (e: IOException) {
                Log.e("HomeViewModel", "Error de red : ${e.message}")
                errorMessageLiveData.postValue("Error de red: ${e.message}")
            } catch (e: Exception){
                Log.e("HomeViewModel", "Error desconocido: ${e.message}")
                errorMessageLiveData.postValue("Error desconocido: ${e.message}")
            }
        }
    }
}