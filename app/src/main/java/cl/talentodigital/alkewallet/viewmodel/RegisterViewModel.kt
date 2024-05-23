package cl.talentodigital.alkewallet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel: ViewModel() {

    val RegistrationValidData = MutableLiveData<Boolean>()

    fun hacerRegistro(firstName: String, lastName: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {

            try {
                //Llamar a la API
                if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    RegistrationValidData.postValue(true)

                } else {
                    RegistrationValidData.postValue(false)
                }

            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                RegistrationValidData.postValue(false)
            }
        }
    }
}
