package cl.talentodigital.alkewallet.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cl.talentodigital.alkewallet.data.model.RegisterRequest
import cl.talentodigital.alkewallet.data.network.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel: ViewModel() {

    val registrationValidData = MutableLiveData<Boolean>()

    fun register(firstName: String, lastName: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            try {

                val response = ApiClient.apiService.register(RegisterRequest(
                    first_name = firstName,
                    last_name = lastName,
                    email = email,
                    password = password
                )
            )
                //comparara datos
                if (response != null) {
                    registrationValidData.postValue(true)
                } else {
                    registrationValidData.postValue(false)
                }

            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                registrationValidData.postValue(false)
            }
        }
    }
}
