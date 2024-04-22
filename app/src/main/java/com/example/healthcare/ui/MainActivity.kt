package com.example.healthcare.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityMainBinding
import com.example.healthcare.general.ui.GeneralUserActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }

<<<<<<< HEAD:app/src/main/java/com/example/healthcare/ui/MainActivity.kt
=======
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, GeneralUserActivity::class.java))
        }

>>>>>>> general:app/src/main/java/com/example/healthcare/MainActivity.kt
    }
}