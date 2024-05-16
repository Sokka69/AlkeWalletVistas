package cl.talentodigital.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.talentodigital.alkewallet.databinding.ActivityLoginBinding


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Vamos a declarar los botones para la interaccion
        val botonYatienesCuenta = findViewById<TextView>(R.id.btn_login2)
        botonYatienesCuenta.setOnClickListener {
            val irLogin = Intent(this, SignUpActivity::class.java)
            startActivity(irLogin)
        }

        val botonLogin = findViewById<TextView>(R.id.btnLogin)
        botonLogin.setOnClickListener {
            val irHomePage = Intent(this, HomePage::class.java)
            startActivity(irHomePage)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}