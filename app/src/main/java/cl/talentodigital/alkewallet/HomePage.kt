package cl.talentodigital.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home_page)

        //Vamos a declarar los botones para la interaccion
        val btnEnviar = findViewById<Button>(R.id.informacion)
        btnEnviar.setOnClickListener {
            val irEnviar = Intent(this, SendMoney::class.java)
            startActivity(irEnviar)
        }

        val btnIngresar = findViewById<Button>(R.id.btn_ingresar)
        btnIngresar.setOnClickListener {
            val irSendMoney = Intent(this, SendMoney::class.java)
            startActivity(irSendMoney)
        }

        val imageUser1 = findViewById<ImageView>(R.id.imageUser1)
        imageUser1.setOnClickListener {
            val irProfilePage = Intent(this, ProfilePage::class.java)
            startActivity(irProfilePage)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}