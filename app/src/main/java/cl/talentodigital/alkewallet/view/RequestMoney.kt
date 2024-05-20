package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivityRequestMoneyBinding

class RequestMoney : AppCompatActivity() {

    private lateinit var binding: ActivityRequestMoneyBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivityRequestMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Vamos a declarar los botones para la interaccion
        binding.btnIngresar.setOnClickListener {
            val irHome = Intent(this, HomePage::class.java)
            startActivity(irHome)
        }
        binding.svgRegreso.setOnClickListener {
            val irHome = Intent(this, HomePage::class.java)
            startActivity(irHome)
        }

    }
}