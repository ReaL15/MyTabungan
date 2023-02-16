package com.example.tabunganku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.tabunganku.aplikasi.DashboardActivity
import com.example.tabunganku.databinding.ActivityEditBinding
import com.example.tabunganku.databinding.ActivityLoginBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding : ActivityEditBinding
    private lateinit var image : ImageView
    private lateinit var namaTabungan : EditText
    private lateinit var targetTabungan : EditText
    private lateinit var nominalPengisian : EditText
    private lateinit var btnSimpan : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        namaTabungan = findViewById(R.id.nama_tabungan)
        targetTabungan = findViewById(R.id.target_tabungan)
        nominalPengisian = findViewById(R.id.nominal_pengisian)
        btnSimpan = findViewById(R.id.btn_Simpan)

        btnSimpan.setOnClickListener(this)

        binding.backButton.setOnClickListener{
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        val cardImage : CardView = findViewById(R.id.card_image)


        cardImage.setOnClickListener {
            image = findViewById(R.id.image)
            uploadImage(image)
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

    override fun onClick(v: View?) {
        saveData()
    }
    private fun saveData(){
        val namaBarang = namaTabungan.text.toString().trim()
        val hargaBarang = targetTabungan.text.toString().trim()
        val uangNominal = nominalPengisian.text.toString().trim()


        if (namaBarang.isEmpty()){
            namaTabungan.error = "Isi Nama Barang!"
            return
        }

        if (hargaBarang.isEmpty()){
            targetTabungan.error = "Isi Target Tabungan!"
            return
        }
        if (uangNominal.isEmpty()){
            nominalPengisian.error = "Isi Nominal Pengisian!"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("items")

        val itmId = ref.push().key

        val itm = Items(itmId,namaBarang,hargaBarang,uangNominal)

        if (itmId != null) {
            ref.child(itmId).setValue(itm).addOnCompleteListener{
                Toast.makeText(applicationContext,"Data erhasil di tambahkan", Toast.LENGTH_SHORT).show()
            }
        }

    }
}