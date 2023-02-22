package com.example.tabunganku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import java.lang.System.err

class EditActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityEditBinding
    private lateinit var namaTabungan: EditText
    private lateinit var targetTabungan: EditText
    private lateinit var nominalPengisian: EditText
    private lateinit var cardHarian: Button
    private lateinit var cardMingguan: Button
    private lateinit var cardTahunan: Button
    private lateinit var btnSimpan: Button
    private var statusHarian: Boolean = false
    private var statusMingguan: Boolean = false
    private var statusTahunan: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        namaTabungan = findViewById(R.id.nama_tabungan)
        targetTabungan = findViewById(R.id.target_tabungan)
        nominalPengisian = findViewById(R.id.nominal_pengisian)
        btnSimpan = findViewById(R.id.btn_Simpan)

        btnSimpan.setOnClickListener(this)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        binding.cardHarian.setOnClickListener {
            if (!statusHarian) {
                binding.cardHarian.setBackgroundResource(R.drawable.button_bg_color)
                statusHarian = true
                statusMingguan = false
                statusTahunan = false
                binding.cardMingguan.setBackgroundResource(R.drawable.button_background)
                binding.cardTahunan.setBackgroundResource(R.drawable.button_background)

            } else {
                binding.cardHarian.setBackgroundResource(R.drawable.button_background)
                statusHarian = false
            }
        }

        binding.cardMingguan.setOnClickListener {
            if (statusMingguan == false) {
                binding.cardMingguan.setBackgroundResource(R.drawable.button_bg_color)
                statusMingguan = true
                statusHarian = false
                statusTahunan = false
                binding.cardHarian.setBackgroundResource(R.drawable.button_background)
                binding.cardTahunan.setBackgroundResource(R.drawable.button_background)
            } else {
                binding.cardMingguan.setBackgroundResource(R.drawable.button_background)
                statusMingguan = false
            }
        }

        binding.cardTahunan.setOnClickListener {
            if (statusTahunan == false) {
                binding.cardTahunan.setBackgroundResource(R.drawable.button_bg_color)
                statusTahunan = true
                statusHarian = false
                statusMingguan = false
                binding.cardMingguan.setBackgroundResource(R.drawable.button_background)
                binding.cardHarian.setBackgroundResource(R.drawable.button_background)
            } else {
                binding.cardTahunan.setBackgroundResource(R.drawable.button_background)
                statusTahunan = false
            }
        }

    }


    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData() {
        val namaBarang = namaTabungan.text.toString().trim()
        val hargaBarang = targetTabungan.text.toString().trim()
        val uangNominal = nominalPengisian.text.toString().trim()


        if (namaBarang.isEmpty()) {
            namaTabungan.error = "Isi Nama Barang!"
            return
        }

        if (hargaBarang.isEmpty()) {
            targetTabungan.error = "Isi Target Tabungan!"
            return
        }
        if (uangNominal.isEmpty()) {
            nominalPengisian.error = "Isi Nominal Pengisian!"
            return
        }

        val ref =
            FirebaseDatabase.getInstance("https://tabunganku-b316d-default-rtdb.asia-southeast1.firebasedatabase.app/")
                .getReference("items")

        val itmId = ref.push().key

        val itm = Items(itmId, namaBarang, hargaBarang, uangNominal,statusHarian,statusMingguan,statusTahunan)

        if (itmId != null) {
            Log.d("Test", itmId)
            ref.child(itmId).setValue(itm).addOnCompleteListener {
                Toast.makeText(this, "Data berhasil di tambahkan", Toast.LENGTH_SHORT).show()

                namaTabungan.text.clear()
                targetTabungan.text.clear()
                nominalPengisian.text.clear()
                binding.cardHarian.setBackgroundResource(R.drawable.button_background)
                binding.cardMingguan.setBackgroundResource(R.drawable.button_background)
                binding.cardTahunan.setBackgroundResource(R.drawable.button_background)


            }.addOnFailureListener { err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_SHORT).show()
                Log.e("ERROR MESSAGE", "saveData: ${err.message}")
            }
        }

    }
}