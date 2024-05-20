package cl.talentodigital.alkewallet.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.alkewallet.databinding.ActivityLoginBinding
import cl.talentodigital.alkewallet.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPreferences: SharedPreferences
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Configuracion Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Implementacion SharedPreferences
        sharedPreferences = getSharedPreferences("AlkeWalet", Context.MODE_PRIVATE)

        val correo = sharedPreferences.getString("correo_ingresado", null)

        if (correo != null){
            binding.txtEmail.setText(correo)

        }
        //Configurar onClick
        binding.btnLogin.setOnClickListener {


            //Vamos a rescartar la informacion que ingreso el usuario
            var correoIngresado = binding.txtEmail.text.toString()
            var passwordIngresado = binding.txtPassword.text.toString()
            //vamos a guardar el correo en los sharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("correo_ingresado", correoIngresado)
            editor.putBoolean("recuerdame", true)
            editor.apply()
        }








        //Vamos a declarar los botones para la interaccion
        /*val botonYatienesCuenta = findViewById<TextView>(R.id.btn_login2)*/
        binding.btnLogin2.setOnClickListener {
            val irLogin = Intent(this, SignUpActivity::class.java)
            startActivity(irLogin)
        }

        binding.btnLogin.setOnClickListener {
            val irHomePage = Intent(this, HomePage::class.java)
            startActivity(irHomePage)
        }
       /* val botonLogin = findViewById<TextView>(R.id.btnLogin)
        botonLogin.setOnClickListener {
            val irHomePage = Intent(this, HomePage::class.java)
            startActivity(irHomePage)
        }*/


    }
}