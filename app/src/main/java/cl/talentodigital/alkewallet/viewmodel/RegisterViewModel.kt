package cl.talentodigital.alkewallet.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RegisterViewModel: ViewModel() {

    val RegistrationValid = MutableLiveData<Boolean>()
  /*  val isRegistrationValid: LiveData<Boolean> get() = RegistrationValid
    fun crearUsuario(firstName: String, lastName: String, email: String, password: String, password1: String) {
        RegistrationValid.value = firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password1.isNotEmpty()

        }*/

    fun hacerRegistro(firstName: String, lastName: String, email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Llamar a la API
                if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                    RegistrationValid.postValue(true)

                } else {
                    RegistrationValid.postValue(false)
                }

            } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                RegistrationValid.postValue(false)
            }
        }
    }

    }
