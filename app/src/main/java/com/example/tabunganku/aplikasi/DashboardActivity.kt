package com.example.tabunganku.aplikasi

import  android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tabunganku.EditActivity
import com.example.tabunganku.Items
import com.example.tabunganku.R
import com.example.tabunganku.databinding.ActivityDashboardBinding
import com.google.firebase.database.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var dbref : DatabaseReference
    private lateinit var itemRecyclerView : RecyclerView
    private lateinit var itemArrayList : ArrayList<Items>

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

       binding.iconMenuTambahkan.setOnClickListener{
           val intent = Intent(this, EditActivity::class.java)
           startActivity(intent)
       }

        itemRecyclerView = findViewById(R.id.recycler_view)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)

        itemArrayList = arrayListOf<Items>()
        getUserData()





    }

    private fun getUserData() {

        dbref = FirebaseDatabase.getInstance("https://tabunganku-b316d-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("items")


        dbref.addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
               if(snapshot.exists()){

                   for (itemSnapshot in snapshot.children){

                       val item = itemSnapshot.getValue(Items::class.java)
                       itemArrayList.add(item!!)
                   }

                   itemRecyclerView.adapter = cardAdapter(itemArrayList)
               }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }


        })
    }
}