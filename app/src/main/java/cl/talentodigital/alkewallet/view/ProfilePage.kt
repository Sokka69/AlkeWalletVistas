package cl.talentodigital.alkewallet.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivityProfilePageBinding

class ProfilePage : AppCompatActivity() {
    private lateinit var binding: ActivityProfilePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Configuracion Binding
        binding = ActivityProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}