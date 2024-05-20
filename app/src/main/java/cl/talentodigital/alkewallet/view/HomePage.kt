package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Vamos a declarar los botones para la interaccion
        binding.btnEnviar.setOnClickListener {
            val irEnviar = Intent(this, SendMoney::class.java)
            startActivity(irEnviar)
        }

       binding.btnIngresar.setOnClickListener {
           val irSendMoney = Intent(this, RequestMoney::class.java)
           startActivity(irSendMoney)
       }

       binding.fotoperfil.setOnClickListener {
           val irProfilePage = Intent(this, ProfilePage::class.java)
           startActivity(irProfilePage)
       }


    }
}