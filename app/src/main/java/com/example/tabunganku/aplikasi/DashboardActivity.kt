package com.example.tabunganku.aplikasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tabunganku.EditActivity
import com.example.tabunganku.R
import com.example.tabunganku.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       binding.iconMenuTambahkan.setOnClickListener{
           val intent = Intent(this, EditActivity::class.java)
           startActivity(intent)
       }





    }
}