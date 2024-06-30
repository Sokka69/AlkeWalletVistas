package cl.talentodigital.alkewallet.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.databinding.ActivityRequestMoneyBinding
import cl.talentodigital.alkewallet.presentation.viewmodel.RequestMoneyViewModel

class RequestMoneyActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRequestMoneyBinding
    private lateinit var requestMoneyViewModel: RequestMoneyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Configuracion Binding
        binding = ActivityRequestMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestMoneyViewModel = ViewModelProvider(this)[RequestMoneyViewModel::class.java]

        // Obtener el nombre del usuario
        val userName =
            "${AlkeWalletApp.userLogged?.first_name} ${AlkeWalletApp.userLogged?.last_name}"
        binding.userName.text = userName
        val userEmail = "${AlkeWalletApp.userLogged?.email}"
        binding.userEmail.text = userEmail

        binding.svgRegreso.setOnClickListener { goToHomePage() }

        //Vamos a declarar los botones para la interaccion
        binding.btnIngresar.setOnClickListener {
            val concept = binding.editTextConcept.text.toString()
            val amountText = binding.editTextAmount.text.toString()

            if (concept.isNotEmpty() && amountText.isNotEmpty()) {
                val amount = amountText.toDouble()
                val accountId = AlkeWalletApp.userAccount?.id
                if (amount != null && accountId != null) {
                    requestMoneyViewModel.requestMoney(accountId, concept, amount)
                } else {
                    Toast.makeText(this, "Error al realizar la solicitud", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this, "Ingrese un concepto y una cantidad", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        requestMoneyViewModel.topUpResultLiveData.observe(this) { paymentResponse ->
            if (paymentResponse != null && paymentResponse.status == 200) {
                Toast.makeText(
                    this,
                    "Solicitud de depósito exitosa: ${paymentResponse.message}",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al realizar la solicitud", Toast.LENGTH_SHORT).show()

            }
        }
        requestMoneyViewModel.errorMessageLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
    private fun goToHomePage() {
        val intent = Intent(this, HomePageActivity::class.java)
        startActivity(intent)
        finish()
    }
}