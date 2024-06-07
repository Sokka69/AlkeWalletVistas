package cl.talentodigital.alkewallet

import android.app.Application
import cl.talentodigital.alkewallet.data.model.UserResponse

class AlkeWalletApp : Application() {

    companion object{
        var userLogged: UserResponse? = null
        var tokenAccess: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        userLogged = null
        tokenAccess = null
    }
}