package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.alkewallet.databinding.ActivitySignUpBinding
import cl.talentodigital.alkewallet.viewmodel.RegisterViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion ViewModel
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.btnCrear.setOnClickListener {

            val firstName = binding.firstName.text.toString().trim()
            val lastName = binding.lastName.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()
            /*val password1 = binding.password1.text.toString().trim()*/

            viewModel.hacerRegistro(firstName, lastName, email, password)

            viewModel.RegistrationValid.observe(this) { valid ->
                if (valid) {
                    val intent = Intent(this, HomePageEmptyCaseActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Favor ingresar los datos", Toast.LENGTH_LONG).show()
                }

            }


            binding.btnLogin2.setOnClickListener {
                val irHome = Intent(this, LoginActivity::class.java)
                startActivity(irHome)
            }
        }

    }

}