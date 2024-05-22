package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivityHomePageEmptyCaseBinding


class HomePageEmptyCaseActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageEmptyCaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Configuracion Binding
        binding = ActivityHomePageEmptyCaseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Configuracion botones para la interaccion
        binding.btnEnviar.setOnClickListener {
            val irEnviar = Intent(this, SendMoneyActivity::class.java)
            startActivity(irEnviar)
        }
        binding.btnIngresar.setOnClickListener {
            val irSendMoney = Intent(this, RequestMoneyActivity::class.java)
            startActivity(irSendMoney)
        }

        binding.fotoperfil.setOnClickListener {
            val irProfilePageActivity = Intent(this, ProfilePageActivity::class.java)
            startActivity(irProfilePageActivity)
        }





    }
}