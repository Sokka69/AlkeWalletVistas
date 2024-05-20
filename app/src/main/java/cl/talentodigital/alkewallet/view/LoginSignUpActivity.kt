package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivityLoginSigupPageBinding


class LoginSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSigupPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivityLoginSigupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion los botones para la interaccion
        binding.btnCrear.setOnClickListener {
        val crear = Intent(this, SignUpActivity::class.java)
            startActivity(crear)
        }

        binding.btnLogin2.setOnClickListener {
            val irLogin = Intent(this, LoginActivity::class.java)
            startActivity(irLogin)
        }





    }

}