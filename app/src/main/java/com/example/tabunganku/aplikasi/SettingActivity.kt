package com.example.tabunganku.aplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tabunganku.LoginAndRegister.LoginActivity
import com.example.tabunganku.R
import com.example.tabunganku.databinding.ActivityDashboardBinding
import com.example.tabunganku.databinding.ActivitySettingBinding
import com.example.tabunganku.helper.PreferenceHelper

class SettingActivity : AppCompatActivity() {

    lateinit var binding: ActivitySettingBinding
    lateinit var sph : PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySettingBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        sph = PreferenceHelper(this)

        binding.tombolLogout.setOnClickListener{
            sph.clear()
            Toast.makeText(applicationContext, "Berhasil Logout", Toast.LENGTH_SHORT).show()
            moveIntent()
        }

    }

    private fun moveIntent() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}