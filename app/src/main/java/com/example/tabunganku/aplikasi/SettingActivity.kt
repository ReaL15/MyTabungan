package com.example.tabunganku.aplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate.*
import com.example.tabunganku.LoginAndRegister.LoginActivity
import com.example.tabunganku.databinding.ActivitySettingBinding
import com.example.tabunganku.helper.Constant
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

        binding.txtEmail.text = sph.getString(Constant.PREF_EMAIL)

        binding.btnBack.setOnClickListener{
            startActivity(Intent(this, DashboardActivity::class.java))
        }

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
