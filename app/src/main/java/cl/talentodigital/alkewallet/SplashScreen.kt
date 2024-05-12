package cl.talentodigital.alkewallet

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.talentodigital.alkewallet.databinding.ActivitySplashScreenBinding
import java.util.Timer
import java.util.TimerTask

class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         * Abiendo la pantalla con un timer
         */
        var task: TimerTask? = object : TimerTask() {
            override fun run() {
                val irHome = Intent(baseContext, LoginSignUpActivity::class.java)

                startActivity(irHome)
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

