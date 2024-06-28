package cl.talentodigital.alkewallet.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import cl.talentodigital.alkewallet.databinding.ActivitySendMoneyBinding
import cl.talentodigital.alkewallet.presentation.viewmodel.SendMoneyViewModel

class SendMoneyActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySendMoneyBinding
    private lateinit var sendMoneyViewModel: SendMoneyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Configuracion Binding
        binding = ActivitySendMoneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Configuracion ViewModel
        sendMoneyViewModel = ViewModelProvider(this)[SendMoneyViewModel::class.java]

        binding.svgRegreso.setOnClickListener { goToHomePage()}

        binding.btnSend.setOnClickListener {
            val concept = binding.noteTransfer.text.toString()
            val amountText = binding.editTextAmount.text.toString()
            if (concept.isNotEmpty() && amountText.isNotEmpty()) {
                val amount = amountText.toDoubleOrNull()
                if (amount != null) {
                    sendMoneyViewModel.sendPayment(concept, amount)

                } else {
                    Toast.makeText(this, "Favor, Ingrese un monto válido", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"Favor, Ingrese todos los datos requeridos", Toast.LENGTH_SHORT).show()
            }
        }
        sendMoneyViewModel.paymentResultLiveData.observe(this) { paymentResponse ->
            if (paymentResponse != null && paymentResponse.message == "OK") {
                Toast.makeText(this, "Pago enviado exitosamente", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al enviar el pago", Toast.LENGTH_SHORT).show()
            }
        }

        sendMoneyViewModel.errorMessageLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }
        private fun goToHomePage() {
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }
}