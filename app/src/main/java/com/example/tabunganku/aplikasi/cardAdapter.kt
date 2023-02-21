package com.example.tabunganku.aplikasi

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

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

        val currentItem = itemList[position]

        holder.namaBarang.text = currentItem.namaBarang
        holder.hargaBarang.text = currentItem.hargaBarang
    }

    override fun getItemCount(): Int {

       return itemList.size
    }

    class myViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val namaBarang : TextView = itemView.findViewById(R.id.text_nama_barang)
        val hargaBarang : TextView = itemView.findViewById(R.id.text_target)


    }
}