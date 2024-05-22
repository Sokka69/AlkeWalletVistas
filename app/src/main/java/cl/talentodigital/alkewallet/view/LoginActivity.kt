package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.alkewallet.R
import cl.talentodigital.alkewallet.databinding.ActivityLoginBinding
import cl.talentodigital.alkewallet.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //Configuracion Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        //Configurar onClick
        binding.btnLogin.setOnClickListener {

            //Vamos a rescartar la informacion que ingreso el usuario
            var correoIngresado = binding.txtEmail.text.toString()
            var passwordIngresado = binding.txtPassword.text.toString()

            viewModel.hacerLogin(correoIngresado, passwordIngresado)
        }
        //Se configura el observador que va a estar observando al sujeto "loginResultLiveData"

            viewModel.loginResultLiveData.observe(this) { loginOk ->
            if (loginOk == true) {
                val irMenuPrincipal = Intent(this, HomePage::class.java)
                startActivity(irMenuPrincipal)
            } else {
                Toast.makeText(this, "Datos Invalidos", Toast.LENGTH_LONG).show()
            }
        }


            binding.btnLogin2.setOnClickListener {
            val irLogin = Intent(this, SignUpActivity::class.java)
            startActivity(irLogin)
            }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

}
