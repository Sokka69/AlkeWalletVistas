package cl.talentodigital.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SendMoney : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_send_money)

        val btnEnviar = findViewById<Button>(R.id.btn_enviar2)
        btnEnviar.setOnClickListener {
            val irHome = Intent(this, HomePage::class.java)
            startActivity(irHome)
        }

        val svgRegreso = findViewById<ImageView>(R.id.svgRegreso)
        svgRegreso.setOnClickListener {
            val irHome = Intent(this, HomePage::class.java)
            startActivity(irHome)
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}