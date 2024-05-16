package cl.talentodigital.alkewallet

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.talentodigital.alkewallet.databinding.ActivityLoginSigupPageBinding


class LoginSignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginSigupPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginSigupPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Vamos a declarar los botones para la interaccion

        binding.btnCrear.setOnClickListener {
        val irCrear = Intent(this, SignUpActivity::class.java)
            startActivity(irCrear)
        }

        /*val btnIrLogin = findViewById<Button>(R.id.btn_crear2)
        btnIrLogin.setOnClickListener {
            val irCrear = Intent(this, SignUpActivity::class.java)
            startActivity(irCrear)
        }*/

        /*//Vamos a declarar los botones para la interaccion
        val lblYatienesCuenta = findViewById<TextView>(R.id.btn_login2)
        lblYatienesCuenta.setOnClickListener {
            val irLogin = Intent(this, LoginActivity::class.java)
            startActivity(irLogin)
        }*/
        binding.btnLogin2.setOnClickListener {
            val irLogin = Intent(this, LoginActivity::class.java)
            startActivity(irLogin)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


    }

}