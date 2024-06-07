package cl.talentodigital.alkewallet.presentation.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivityProfilePageBinding

class ProfilePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfilePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivityProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}