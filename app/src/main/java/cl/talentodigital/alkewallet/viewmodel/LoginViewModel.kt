package cl.talentodigital.alkewallet.viewmodel

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*View model escargado de hacer el login de la APP*/
class LoginViewModel : ViewModel() {

    val loginResultLiveData = MutableLiveData<Boolean>()

    /**
     * funcion que implementa una corrrutina para llamar a la Api
     */
    fun hacerLogin(email: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                //Aca nosotros vamos a llamar a la API
                //val response = ApiClient.apiService.login(username, password)
                if (email == "test@test.cl" && password =="1234") {
                   loginResultLiveData.postValue(true)

                    } else {
                        loginResultLiveData.postValue(false)
                    }

                } catch (e: Exception) {
                //aqui si hay un error se ejecuta este codigo
                    loginResultLiveData.postValue(false)
            }
        }
    }
}