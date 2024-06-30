package cl.talentodigital.alkewallet.presentation.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.databinding.ActivityProfilePageBinding

class ProfilePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Configuracion Binding
        binding = ActivityProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener el nombre del usuario desde GlobalClassApp
        val userName = "${AlkeWalletApp.userLogged?.first_name} ${AlkeWalletApp.userLogged?.last_name}"
        binding.txtUserName.text = userName


    }
}