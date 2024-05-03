package cl.talentodigital.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class LoginSignUpActivity : AppCompatActivity() {


    // vamos a definir las variables del boton (paso 1)
    var botonIrLogin : TextView? = null
    var botonIrCrear : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_sigup_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // instanciamos el id al botonIrLogin (paso2)

        botonIrLogin = findViewById(R.id.btn_login2)
        botonIrCrear = findViewById(R.id.btn_crear2)


        // entonces el boton hara  funcion click (paso3)

        botonIrLogin?.setOnClickListener { irPaginaLogin() }
        botonIrCrear?.setOnClickListener { irPaginaRegistro() }



    }

    private fun irPaginaRegistro() {
        val i = Intent(this, SignUpActivity ::class.java)
        startActivity(i)
    }

    // Se crea funcion de boton llame a nueva pantalla
    private fun irPaginaLogin() {
        val i = Intent(this, LoginActivity ::class.java)
        startActivity(i)
    }
}