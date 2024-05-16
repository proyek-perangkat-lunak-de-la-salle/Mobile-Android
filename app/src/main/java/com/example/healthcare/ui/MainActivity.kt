package com.example.healthcare.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.healthcare.R
import com.example.healthcare.data.pref.UserModel
import com.example.healthcare.data.remote.model.UserLogin
import com.example.healthcare.databinding.ActivityMainBinding
import com.example.healthcare.helper.Result
import com.example.healthcare.helper.ViewModelFactory
import com.example.healthcare.ui.admin.AdminMainActivity
import com.example.healthcare.ui.general.fragment.GeneralUserActivity
import com.example.healthcare.ui.staff.StaffWilayahMainActivity

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
        val factory = ViewModelFactory.getInstance(this)
        val viewModel: MainViewModel by viewModels {
            factory
        }

        setLoading(false)

        binding.btnLogin.setOnClickListener {
            val email = binding.emailEdt.text.toString().trim()
            val password = binding.passwordEdt.text.toString().trim()

            if (email.isEmpty() || email.isBlank()) {
                binding.emailEdtLayout.error = resources.getString(R.string.hint_field)
            }

            if (password.isEmpty() || password.isBlank()) {
                binding.passwordEdt.error = resources.getString(R.string.hint_field)
            }

            val user = UserLogin(email, password)
            viewModel.loginUser(user).observe(this) {
                when (it) {
                    is Result.Error -> {
                        setLoading(false)
                        showToast(it.error)
                    }

                    Result.Loading -> {
                        setLoading(true)
                    }

                    is Result.Success -> {
                        setLoading(false)
                        val session = UserModel(it.data.token, it.data.token, true)
                        viewModel.saveSession(session)

                        when (it.data.role) {
                            ADMIN_WILAYAH -> {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        StaffWilayahMainActivity::class.java
                                    )
                                )
                            }

                            ADMIN_PAROKI -> {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        AdminMainActivity::class.java
                                    )
                                )
                            }

                            PENGGUNA_UMUM -> {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        GeneralUserActivity::class.java
                                    )
                                )
                            }
                        }
                    }
                }

            }
        }

        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
        }

    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBarMain.visibility = View.VISIBLE
        else binding.progressBarMain.visibility = View.INVISIBLE
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        private const val ADMIN_WILAYAH = "Admin Wilayah"
        private const val ADMIN_PAROKI = "Admin Paroki"
        private const val PENGGUNA_UMUM = "Pengguna Umum"
    }

}