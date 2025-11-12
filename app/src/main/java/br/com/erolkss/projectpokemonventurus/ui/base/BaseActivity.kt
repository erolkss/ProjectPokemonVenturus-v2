package br.com.erolkss.projectpokemonventurus.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.com.erolkss.projectpokemonventurus.R

open class BaseActivity : AppCompatActivity() {

    private var loadingView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_base)
        addLoadingOverlay()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun addLoadingOverlay() {
        val decorView = window.decorView as FrameLayout
        loadingView = LayoutInflater.from(this).inflate(R.layout.layout_loading, decorView, false)
        decorView.addView(loadingView)
    }

    fun showLoading() {
        loadingView?.let { view ->
            val pokeball = view.findViewById<View>(R.id.loadingPokeball)
            val rotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation)
            pokeball.startAnimation(rotate)
            view.visibility = View.VISIBLE
        }
    }

    fun hideLoading() {
        loadingView?.let { view ->
            val pokeball = view.findViewById<View>(R.id.loadingPokeball)
            pokeball.clearAnimation()
            view.visibility = View.GONE
        }
    }
}