package com.example.tabunganku.aplikasi

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tabunganku.Items
import com.example.tabunganku.R
import org.w3c.dom.Text

class cardAdapter(private val itemList : ArrayList<Items>) : RecyclerView.Adapter<cardAdapter.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_card_berlangsung,
        parent,false)
        return myViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = itemList[position]

        holder.namaBarang.text = currentItem.namaBarang
        holder.hargaBarang.text = currentItem.hargaBarang

        val estimasi = try {
            currentItem.hargaBarang?.toInt()!! / currentItem.uangNominal?.toInt()!!
        } catch (e: Exception) {
            print(e)
        }

        val harian = currentItem.intervalHarian
        val mingguan = currentItem.intervalMingguan


        if (harian == true){
            holder.textEstimasi.text = "${estimasi.toString()} Hari Lagi"
        } else if (mingguan == true){
            holder.textEstimasi.text = "${estimasi.toString()} Minggu Lagi"
        } else{
            holder.textEstimasi.text = "${estimasi.toString()} Tahun Lagi"
        }

    }

    override fun getItemCount(): Int {
       return itemList.size
    }

    class myViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val namaBarang : TextView = itemView.findViewById(R.id.text_nama_barang)
        val hargaBarang : TextView = itemView.findViewById(R.id.text_target)
        val textEstimasi : TextView = itemView.findViewById(R.id.text_estimasi)



    }
}