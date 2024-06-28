package cl.talentodigital.alkewallet.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivitySignUpBinding
import cl.talentodigital.alkewallet.presentation.viewmodel.RegisterViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Configuracion Binding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion ViewModel
        viewModel = RegisterViewModel()

        binding.btnLoginCta.setOnClickListener { registerToLogin() }

        binding.btnCrear.setOnClickListener { register() }


        viewModel.registerResultLiveData.observe(this) { valid ->
            if (valid) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_LONG).show()
                goToHomePage()
            } else {
                Toast.makeText(this, "Error en el registro", Toast.LENGTH_LONG).show()

            }
        }
    }

    private fun register() {
        val firstName = binding.firstName.text.toString()
        val lastName = binding.lastName.text.toString()
        val email = binding.email.text.toString()
        val password = binding.password.text.toString()

        viewModel.register(firstName, lastName, email, password)

    }

    private fun goToHomePage() {

        val intent = Intent(this, HomePageEmptyCaseActivity::class.java)
        startActivity(intent)
    }

    private fun registerToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}