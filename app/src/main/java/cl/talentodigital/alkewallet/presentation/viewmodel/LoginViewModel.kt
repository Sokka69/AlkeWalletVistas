package cl.talentodigital.alkewallet.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.talentodigital.alkewallet.data.model.LoginRequest
import cl.talentodigital.alkewallet.data.model.UserResponse
import cl.talentodigital.alkewallet.data.network.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*View model escargado de hacer el login de la APP*/
class LoginViewModel (application: Application) : AndroidViewModel(application){

    /**
     * View model encargado de hacer el Login de la app
     */
    val loginResultLiveData = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>() // Estado de carga
    val userDataLiveData = MutableLiveData<UserResponse?>() // LiveData para los datos del usuario




        lateinit var accessTokenVm: String
        lateinit var user: String
        private val sharedPreferences = application.getSharedPreferences("AlkeWallet",
            Context.MODE_PRIVATE)


        /**
         * funcion que implementa una corrrutina para llamar a la Api
         */

        fun login(email: String, password: String) {
            loadingLiveData.postValue(true)

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    // Instancia de Retrofit
                    val response = ApiClient.apiService.login(LoginRequest(email, password))
                    if (response.accessToken != null) {

                        // Llamada al servicio
                        accessTokenVm = response.accessToken

                        //guardamos el accesToken en SharedPreferent
                        val editor = sharedPreferences.edit()
                        editor.putString("accessToken", response.accessToken)
                        editor.apply()
                        loginResultLiveData.postValue(true)
                    } else {
                        loginResultLiveData.postValue(true)

                    }
                } catch (e: Exception) {
                    loginResultLiveData.postValue(false)
                    Log.e("Errores", "Error: ${e.message}")
                }

            }

        }

        fun getUserData() {
            loadingLiveData.postValue(true)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = ApiClient.apiService.getUserData("Bearer $accessTokenVm")

                    if (response.isSuccessful) {
                        val dataUser = response.body()
                        userDataLiveData.postValue(dataUser)
                    } else {
                        userDataLiveData.postValue(null)
                    }


                } catch (e: Exception) {
                    // aquí si hay un error se ejecuta este código
                    Log.e("Errores", "Error: ${e.message}")
                    userDataLiveData.postValue(null)
                }
            }
        }
    }


