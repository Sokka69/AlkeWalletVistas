package cl.talentodigital.alkewallet.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cl.talentodigital.alkewallet.AlkeWalletApp
import cl.talentodigital.alkewallet.databinding.ActivityHomePageBinding
import cl.talentodigital.alkewallet.presentation.view.adapter.TransactionAdapter
import cl.talentodigital.alkewallet.presentation.viewmodel.AssignAccountViewModel
import cl.talentodigital.alkewallet.presentation.viewmodel.HomeViewModel
import cl.talentodigital.alkewallet.presentation.viewmodel.TransactionViewModel

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var assignAccountViewModel: AssignAccountViewModel
    private lateinit var transactionAdapter: TransactionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Configuracion Binding
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        assignAccountViewModel = ViewModelProvider(this)[AssignAccountViewModel::class.java]
        transactionViewModel = ViewModelProvider(this)[TransactionViewModel::class.java]



        // Obtener el nombre del usuario desde GlobalClassApp
        val userName = "Hola, ${AlkeWalletApp.userLogged?.first_name} !"
        binding.txtUserName.text = userName

        binding.btnEnviar.setOnClickListener { goToSendMoney() }
        binding.btnIngresar.setOnClickListener { goToAddMoney() }
        binding.imgFotoUser.setOnClickListener { goToProfile() }


        homeViewModel.userBalanceLiveData.observe(this) { balance ->
            binding.userSaldo.text = "$$balance"
        }

        homeViewModel.errorMessageLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        assignAccountViewModel.accountCreatedLiveData.observe(this) { accountCreated ->
            if (accountCreated) {
                Toast.makeText(this, "Cuenta asignada exitosamente", Toast.LENGTH_SHORT).show()
                //aqui volveremos a cargar el homePageActivity
                val intent = Intent(this, HomePageActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Error al asignar cuenta", Toast.LENGTH_SHORT).show()
            }
        }

        assignAccountViewModel.errorMessageLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
        transactionViewModel.transactionsLiveData.observe(this) { transactions ->
            if (transactions.isNotEmpty()) {
                val adapter = TransactionAdapter(transactions)
                binding.recyclerListUser.adapter = adapter
                binding.recyclerListUser.layoutManager = LinearLayoutManager(this)

            }
        }
        transactionViewModel.errorMessageLiveData.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }

        // Verificar la cuenta del usuario
        homeViewModel.checkUserAccount()

        // Obtener transacciones
        transactionViewModel.fetchTransactions()
    }

    private fun goToProfile() {
        val intent = Intent(this, ProfilePageActivity::class.java)
        startActivity(intent)
    }

    private fun goToAddMoney() {
        val intent = Intent(this, RequestMoneyActivity::class.java)
        startActivity(intent)
    }

    private fun goToSendMoney() {
        val intent = Intent(this, SendMoneyActivity::class.java)
        startActivity(intent)

    }


}