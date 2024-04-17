package com.example.healthcare.staff

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.healthcare.R
import com.example.healthcare.databinding.ActivityStaffWilayahMainBinding

class StaffWilayahMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStaffWilayahMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStaffWilayahMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.ivStatistikWilayah.setOnClickListener {

        }

        binding.ivUploadCsv.setOnClickListener {

        }
    }
}