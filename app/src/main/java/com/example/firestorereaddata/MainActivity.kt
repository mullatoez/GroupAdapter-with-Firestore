package com.example.firestorereaddata

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firestorereaddata.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.user_row.view.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerviewMain.layoutManager = LinearLayoutManager(this)
        fetchData()

    }

    private fun fetchData() {
        db.collection("users")
            .get()
            .addOnSuccessListener {

                val adapter = GroupAdapter<ViewHolder>()

                for(document in it){
                //We need to pass the class object here
                    val user = it.toObjects(User::class.java)
                    adapter.add(UserItem(user))
                }

                binding.recyclerviewMain.adapter = adapter
            }
    }
}
