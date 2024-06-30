package cl.talentodigital.alkewallet.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.data.model.LoginRequest
import cl.talentodigital.alkewallet.data.model.UserResponse
import cl.talentodigital.alkewallet.data.network.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException


/*View model escargado de hacer el login de la APP*/
class LoginViewModel(application: Application) : AndroidViewModel(application) {

    /**
     * ViewModel encargado de hacer el Login de la app
     */

    // LiveData para el resultado del login
    val loginResultLiveData = MutableLiveData<Boolean>()
    // LiveData para el estado de carga
    val loadingLiveData = MutableLiveData<Boolean>()
    // LiveData para los datos del usuario
    val userDataLiveData = MutableLiveData<UserResponse?>()


    lateinit var accessTokenVm: String
    lateinit var user: String
    //Guardado de el token
    private val sharedPreferences = application.getSharedPreferences(
        "AlkeWallet",
        Context.MODE_PRIVATE
    )


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

                    //Guardado del accesToken en SharedPreferent
                    val editor = sharedPreferences.edit()
                    editor.putString("accessToken", response.accessToken)
                    editor.apply()

                    loginResultLiveData.postValue(true)
                } else {
                    loginResultLiveData.postValue(true)

                }
            } catch (e: IOException) {
                loginResultLiveData.postValue(false)
                Log.e("Error", "Error de red: ${e.message}")

            } catch (e: Exception) {
                // Manejar otros errores
                Log.e("Error", "Error desconocido: ${e.message}")
                loginResultLiveData.postValue(false)
            } finally {
                loadingLiveData.postValue(false) // Indicar fin de carga
            }
        }
    }
    //funcion para obtener los datos del usuario
    fun getUserData() {
        loadingLiveData.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiClient.apiService.getUserData("Bearer $accessTokenVm")

                if (response.isSuccessful) {
                    val userData = response.body()
                    userDataLiveData.postValue(userData)

                    AlkeWalletApp.userLogged = userData

                } else {
                    userDataLiveData.postValue(null)
                }
            } catch (e: IOException) {
                // aquí si hay un error se ejecuta este código
                Log.e("Error", "Error de red: ${e.message}")
                userDataLiveData.postValue(null)

            } catch (e: Exception) {
                // Manejar otros errores
                Log.e("Error", "Error desconocido: ${e.message}")
                userDataLiveData.postValue(null)

            } finally {
                loadingLiveData.postValue(false)
            }
        }
    }
}


