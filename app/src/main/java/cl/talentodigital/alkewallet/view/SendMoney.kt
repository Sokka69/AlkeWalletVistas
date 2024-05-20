package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivitySendMoneyBinding

class SendMoney : AppCompatActivity() {

    private lateinit var binding: ActivitySendMoneyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Vamos a declarar los botones para la interaccion
        binding.btnEnviar2.setOnClickListener {
            val irHome = Intent(this, HomePage::class.java)
            startActivity(irHome)
        }
        binding.svgRegreso.setOnClickListener {
            val irHome = Intent(this, HomePage::class.java)
            startActivity(irHome)
        }


    }
}