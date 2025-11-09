package br.com.erolkss.projectpokemonventurus.ui.splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.erolkss.projectpokemonventurus.MainActivity
import br.com.erolkss.projectpokemonventurus.R
import br.com.erolkss.projectpokemonventurus.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({

            binding.imagePokeballSplashActivity.animate()
                .alpha(0f)
                .setDuration(500)
                .withEndAction {

                    binding.imagePokeballSplashActivity.setImageResource(R.drawable.pokeball_transparent)
                    binding.main.setBackgroundColor("#ffe336".toColorInt())

                    val params = binding.imagePokeballSplashActivity.layoutParams
                    params.width = (180 * resources.displayMetrics.density).toInt()
                    params.height = (180 * resources.displayMetrics.density).toInt()
                    binding.imagePokeballSplashActivity.layoutParams = params

                    binding.imagePokeballSplashActivity.animate()
                        .alpha(1f)
                        .setDuration(500)
                        .start()

                    val rotate = ObjectAnimator.ofFloat(
                        binding.imagePokeballSplashActivity,
                        "rotation",
                        0f,
                        360f
                    )
                    rotate.duration = 1000
                    rotate.repeatCount = 4
                    rotate.start()

                    Handler(Looper.getMainLooper()).postDelayed({
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }, 4000)
                }
                .start()

        }, 3000)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}