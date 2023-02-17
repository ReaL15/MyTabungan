package com.example.tabunganku.aplikasi
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tabunganku.EditActivity
import com.example.tabunganku.LoginAndRegister.LoginActivity
import com.example.tabunganku.databinding.ActivityDashboardBinding
import com.example.tabunganku.helper.PreferenceHelper

class DashboardActivity : AppCompatActivity() {

    lateinit var binding: ActivityDashboardBinding
    lateinit var sph : PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.iconMenuTambahkan.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }
}