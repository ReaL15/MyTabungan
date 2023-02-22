package com.example.tabunganku.LoginAndRegister
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.example.tabunganku.aplikasi.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.tabunganku.databinding.ActivityLoginBinding
import com.example.tabunganku.helper.Constant
import com.example.tabunganku.helper.PreferenceHelper

class LoginActivity : AppCompatActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth
    lateinit var sph: PreferenceHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.forgotPassword.setOnClickListener {
            val intent = Intent(this, ResetPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.tvToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val password = binding.edtPasswordLogin.text.toString()

            //Validasi email
            if (email.isEmpty()) {
                binding.edtEmailLogin.error = "Email Harus Diisi"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            //Validasi email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.edtEmailLogin.error = "Email Tidak Valid"
                binding.edtEmailLogin.requestFocus()
                return@setOnClickListener
            }

            //Validasi password
            if (password.isEmpty()) {
                binding.edtPasswordLogin.error = "Password Harus Diisi"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }

            LoginFirebase(email,password)

        }
            sph = PreferenceHelper(this)

        }


        private fun LoginFirebase(email: String, password: String) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Selamat datang $email", Toast.LENGTH_SHORT).show()
                        sph.put(Constant.PREF_EMAIL, email)
                        sph.put(Constant.PREF_IS_LOGIN, true)
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }

        }

    override fun onStart() {
        super.onStart()
        if (sph.getBoolean(Constant.PREF_IS_LOGIN)) {
            moveIntent()
        }

    }

    private fun moveIntent() {
        startActivity(Intent(this, DashboardActivity::class.java))
        finish()
    }

    }