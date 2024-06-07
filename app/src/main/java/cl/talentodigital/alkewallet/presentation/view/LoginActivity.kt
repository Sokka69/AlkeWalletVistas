package cl.talentodigital.alkewallet.presentation.view


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.databinding.ActivityLoginBinding
import cl.talentodigital.alkewallet.presentation.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configuracion Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion ViewModel
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        //Configurar onClick
        binding.btnLogin.setOnClickListener { login() }


        // Configurar Observer
        viewModel.loginResultLiveData.observe(this) { loginOK ->
            if (loginOK) {
                //Vamos a guardar dato en la variable global de la aplicación
                AlkeWalletApp.tokenAccess = viewModel.accessTokenVm
                //Llamada funcion para obtener la informacion del user
                viewModel.getUserData()
            } else {
                Toast.makeText(this, "Error en el Login", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.userDataLiveData.observe(this) { userResponse ->
            if (userResponse != null) {
                goToHomePage()

            } else {
                Toast.makeText(
                    this, "Error al obtener la informacion del usuario", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun goToRegisterActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun goToHomePage() {
        val intent = Intent(this, HomePageActivity::class.java)
        startActivity(intent)
    }

    private fun login() {
        val emailEnter = binding.txtEmail.text.toString()
        val passwordEnter = binding.txtPassword.text.toString()

        if (emailEnter != null && passwordEnter != null) {

            viewModel.login(emailEnter, passwordEnter)

        } else {
            Toast.makeText(this, "Los datos no pueden estar vacios", Toast.LENGTH_SHORT).show()
        }
    }
}
