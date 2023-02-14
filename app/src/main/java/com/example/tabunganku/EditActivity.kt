package com.example.tabunganku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.example.tabunganku.aplikasi.DashboardActivity
import com.example.tabunganku.databinding.ActivityEditBinding
import com.example.tabunganku.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference

class EditActivity : AppCompatActivity() {

    lateinit var binding : ActivityEditBinding
    private lateinit var image : ImageView
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.backButton.setOnClickListener{
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        val cardImage : CardView = findViewById(R.id.card_image)


        cardImage.setOnClickListener {
            image = findViewById(R.id.image)
            uploadImage(image)
        }

        binding.buttonSimpan.setOnClickListener {

            val namaTabungan = binding.namaTabungan.text.toString()
            val targetTabungan = binding.targetTabungan
            val nominalPengisian = binding.nominalPengisian
        }

    }

    private fun uploadImage(image: ImageView?) {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1){
             image.setImageURI(data?.data)
        }
    }
}