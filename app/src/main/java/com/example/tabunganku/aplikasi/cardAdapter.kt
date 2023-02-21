package com.example.tabunganku.aplikasi

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tabunganku.Items
import com.example.tabunganku.R
import org.w3c.dom.Text

class cardAdapter(private val itemList : ArrayList<Items>) : RecyclerView.Adapter<cardAdapter.myViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {

       return itemList.size
    }

    class myViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val namaBarang : TextView = itemView.findViewById(R.id.text_nama_barang)
        val hargaBarang : TextView = itemView.findViewById(R.id.text_target)


    }
}