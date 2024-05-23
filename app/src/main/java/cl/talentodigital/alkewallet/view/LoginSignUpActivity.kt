package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivityLoginSignUpBinding


class LoginSignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivityLoginSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion los botones para la interaccion
        binding.btnCrear.setOnClickListener {

        val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin2.setOnClickListener {
            val irLogin = Intent(this, LoginActivity::class.java)
            startActivity(irLogin)
        }





    }

}