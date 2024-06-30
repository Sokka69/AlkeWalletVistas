package cl.talentodigital.alkewallet.presentation.view


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.util.PatternsCompat
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.databinding.ActivityLoginBinding
import cl.talentodigital.alkewallet.presentation.viewmodel.LoginViewModel
import cl.talentodigital.alkewallet.presentation.viewmodel.LoginViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Configuracion Binding
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModelFactory = LoginViewModelFactory(application)


        //Configuracion ViewModel
        viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]

        val sharedPreferences = getSharedPreferences("AlkeWallet", MODE_PRIVATE)
        val emailEnter = sharedPreferences.getString("email", null)
        if (emailEnter != null) {
            binding.textFieldEmail.setText(emailEnter)
        }

        //Configurar onClick
        binding.btnCrearCuenta.setOnClickListener { goToRegisterActivity() }
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
                Log.d(
                    "UserData",
                    "Usuario: ${userResponse.first_name} Apellido: ${userResponse.last_name}"
                )
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

        val emailEnter = binding.textFieldEmail.text.toString()
        val passwordEnter = binding.textFieldPass.text.toString()


        if (validateEmail(binding.textFieldEmail, binding.textFieldEmailError) &&
            validatePass(binding.textFieldPass, binding.textFieldPassError)) {

            val sharedPreferences = getSharedPreferences("AlkeWallet", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("email", emailEnter)
            editor.apply()

            viewModel.login(emailEnter, passwordEnter)
            Log.d("prueba", "Email: $emailEnter, Password: $passwordEnter")

        } else {
            Toast.makeText(this, "Los datos no pueden estar vacios", Toast.LENGTH_SHORT).show()
        }

    }

    private fun validateEmail(email: TextInputEditText, email2: TextInputLayout): Boolean {
        val emailText = email.text.toString().trim()
        return when {
            emailText.isEmpty() -> {
                email2.error = "Requerido"
                false
            }

            !PatternsCompat.EMAIL_ADDRESS.matcher(emailText).matches() -> {
                email2.error = "Correo no válido"
                false
            }

            else -> {
                email2.error = null
                true
            }
        }
    }

    private fun validatePass(pass: TextInputEditText, errorPassTF: TextInputLayout): Boolean {
        val passText = pass.text.toString().trim()
        return when {
            passText.isEmpty() -> {
                errorPassTF.error = "Contraseña requerida"
                false
            }

            else -> {
                errorPassTF.error = null
                true
            }
        }
    }

}
