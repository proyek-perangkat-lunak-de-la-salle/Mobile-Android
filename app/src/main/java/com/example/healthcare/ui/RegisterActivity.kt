package com.example.healthcare.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.healthcare.R
import com.example.healthcare.data.remote.model.UserRegister
import com.example.healthcare.databinding.ActivityRegisterBinding
import com.example.healthcare.helper.Result
import com.example.healthcare.helper.ViewModelFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var nama: String
    private lateinit var username: String
    private lateinit var password: String
    private lateinit var confPassword: String
    private lateinit var email: String
    private var age: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val factory = ViewModelFactory.getInstance()
        val viewModel: RegisterViewModel by viewModels {
            factory
        }

        setLoading(false)

        binding.btnRegister.setOnClickListener {
            nama = binding.namaEdt.text.toString().trim()
            username = binding.usernameEdt.text.toString().trim()
            password = binding.passwordEdt.text.toString().trim()
            confPassword = binding.konfirmasiPasswordEdt.text.toString().trim()
            email = binding.emailEdt.text.toString().trim()

            val roleArray = resources.getStringArray(R.array.tipe_pengguna)
            val roleAdapter = ArrayAdapter(
                this, android.R.layout.simple_dropdown_item_1line,
                roleArray
            )
            binding.autoCompleteTipePengguna.setAdapter(roleAdapter)

            age = binding.usiaEdt.text.toString().trim().toInt()

            val wilayahArray = resources.getStringArray(R.array.pilih_wilayah)
            val wilayahAdapter = ArrayAdapter(
                this, android.R.layout.simple_dropdown_item_1line,
                wilayahArray
            )
            binding.autoCompletePilihWilayah.setAdapter(wilayahAdapter)

            if (nama.isEmpty() || nama.isBlank()) {
                binding.namaEdt.error = resources.getString(R.string.hint_field)
            }

            if (username.isEmpty() || username.isBlank()) {
                binding.usernameEdt.error = resources.getString(R.string.hint_field)
            }

            if (password.isEmpty() || password.isBlank()) {
                binding.passwordEdt.error = resources.getString(R.string.hint_field)
            }

            if (confPassword.isEmpty() || confPassword.isBlank()) {
                binding.konfirmasiPasswordEdt.error = resources.getString(R.string.hint_field)
            }

            if (email.isEmpty() || email.isBlank()) {
                binding.emailEdt.error = resources.getString(R.string.hint_field)
            }

            if (age.toString().isEmpty() || age.toString().isBlank()) {
                binding.usiaEdt.error = resources.getString(R.string.hint_field)
            }

            if (binding.autoCompletePilihWilayah.text.toString().isEmpty()) {
                binding.autoCompletePilihWilayah.error = resources.getString(R.string.hint_field)
            }

            if (binding.autoCompleteTipePengguna.text.toString().isEmpty()) {
                binding.autoCompleteTipePengguna.error = resources.getString(R.string.hint_field)
            }

            val role = binding.autoCompleteTipePengguna.text.toString()
            val idWilayah = findIdWilayah(binding.autoCompletePilihWilayah.text.toString())

            val user =
                UserRegister(nama, username, password, confPassword, email, role, age, idWilayah)
            viewModel.registerUser(user).observe(this) {
                when (it) {
                    Result.Loading -> {
                        setLoading(true)
                    }

                    is Result.Success -> {
                        setLoading(false)
                        showDialog(it.data.message)
                    }

                    is Result.Error -> {
                        setLoading(false)
                        showToast(it.error)
                    }
                }
            }
        }

    }

    private fun setLoading(isLoading: Boolean) {
        if (isLoading) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.INVISIBLE
    }


    private fun showDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Registrasi berhasil")
        builder.setMessage("Kembali ke halaman login?")
        builder.setPositiveButton("Ya") { _, _ ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun findIdWilayah(namaWilayah: String): Int {
        val array = resources.getStringArray(R.array.pilih_wilayah)
        return array.indexOf(namaWilayah)
    }
}