package cl.talentodigital.alkewallet.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import cl.talentodigital.alkewallet.databinding.ActivitySplashScreenBinding
import java.util.Timer
import java.util.TimerTask

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        //Se declara la imagen como una variable
        binding.logoApp.setOnClickListener {
            val abrirPantallaLogin = Intent(this, LoginSignUpActivity ::class.java)
            startActivity(abrirPantallaLogin)
            finish()
        }

        /**
         * Abiendo la pantalla con un timer
         */
        var task: TimerTask? = object : TimerTask() {
            override fun run() {
                val irHome = Intent(baseContext, LoginSignUpActivity::class.java)
                startActivity(irHome)
                finish()
            }
        }
        val timer = Timer()
        timer.schedule(task, 3000)



            /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets  */
        }





}

