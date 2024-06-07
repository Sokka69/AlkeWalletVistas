package cl.talentodigital.alkewallet.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cl.talentodigital.alkewallet.databinding.ActivityHomePageBinding
import cl.talentodigital.alkewallet.data.model.Transaction
import cl.talentodigital.alkewallet.presentation.view.adapter.TransactionAdapter

class HomePageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Configuracion Binding
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //Vamos a declarar los botones para la interaccion
        binding.btnEnviar.setOnClickListener {
            val irEnviar = Intent(this, SendMoneyActivity::class.java)
            startActivity(irEnviar)
        }

        // Simulated list of transactions with URLs for user images
        val transactions = listOf(
            Transaction("Sara ", "Ibrahim", "2024-05-19","$200.0" ,"https://i.ibb.co/8Kr66KJ/user-2.png"),
            Transaction("Reem ", "Khaled", "2024-05-19", "$130.0","https://i.ibb.co/XpXGQRv/user-1.png"),
            Transaction("Ahmad ", "Ibrahim", "2024-05-19","-$50.0" ,"https://i.ibb.co/k2ZWbh7/user-3.png"),
            Transaction("Yara ", "Khalil", "2024-05-19","$300.0" ,"https://i.ibb.co/QbmNchQ/user-4.png"),
            Transaction("Hiba ", "Saleh", "2024-05-18","$50.0" ,"https://i.ibb.co/1vFsvdF/user-5.png"),
            Transaction("Ahmad ", "Ibrahim", "2024-05-18","$450.0" ,"https://i.ibb.co/k2ZWbh7/user-3.png"),
            Transaction("Reem ", "Khaled", "2024-05-18", "-$30.0","https://i.ibb.co/XpXGQRv/user-1.png"),
            Transaction("Yara ", "Khalil", "2024-05-18","$100.0" ,"https://i.ibb.co/QbmNchQ/user-4.png"),
            Transaction("Hiba ", "Saleh", "2024-05-17","$150.0" ,"https://i.ibb.co/1vFsvdF/user-5.png"),
            Transaction("Sara ", "Ibrahim", "2024-05-17","$100.0" ,"https://i.ibb.co/8Kr66KJ/user-2.png"),
            Transaction("Ahmad ", "Ibrahim", "2024-05-17","-$50.0" ,"https://i.ibb.co/k2ZWbh7/user-3.png"),
            Transaction("Yara ", "Khalil", "2024-05-19","$100.0" ,"https://i.ibb.co/QbmNchQ/user-4.png"),
            Transaction("Sara ", "Ibrahim", "2024-05-16","-$150.0" ,"https://i.ibb.co/8Kr66KJ/user-2.png"),
            Transaction("Hiba ", "Saleh", "2024-05-16","$50.0" ,"https://i.ibb.co/1vFsvdF/user-5.png")
        )
        // Setting up the RecyclerView
        binding.recyclerListUser.layoutManager = LinearLayoutManager(this)
        binding.recyclerListUser.adapter = TransactionAdapter(transactions)



       binding.btnIngresar.setOnClickListener {
           val irSendMoney = Intent(this, RequestMoneyActivity::class.java)
           startActivity(irSendMoney)
       }

       binding.fotoperfil.setOnClickListener {
           val irProfilePageActivity = Intent(this, ProfilePageActivity::class.java)
           startActivity(irProfilePageActivity)
       }
    }
}