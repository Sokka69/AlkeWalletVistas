package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion botones para la interaccion
        binding.btnCrear.setOnClickListener {
            val irLogin = Intent(this, HomePageEmptyCase::class.java)
            startActivity(irLogin)
        }

        binding.btnLogin2.setOnClickListener {
            val irHomePage = Intent(this, LoginActivity::class.java)
            startActivity(irHomePage)
        }

    }
}