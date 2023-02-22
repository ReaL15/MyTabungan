package com.example.tabunganku.aplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tabunganku.R
import com.example.tabunganku.databinding.ActivityTercapaiBinding

class TercapaiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTercapaiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTercapaiBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.backButtonTercapai.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)

        }
    }
}