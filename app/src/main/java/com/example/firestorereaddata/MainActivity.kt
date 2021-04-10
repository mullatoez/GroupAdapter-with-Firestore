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

//let's create an adapter

class UserItem(val user: List<User>): Item<ViewHolder>(){

    override fun getLayout(): Int {
       return R.layout.user_row
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        //Let's populate our views using data from firestore

        val user = user[position]
        viewHolder.itemView.textView_firstname.text = user.firstname
        viewHolder.itemView.textView_lastname.text = user.lastname
        viewHolder.itemView.textView_birthplace.text = user.birthplace
    }

}

class User(val firstname: String, val lastname: String, val birthplace: String){
    constructor(): this("","","")
    //Empty constructor needed for firestore
}

//LET'S RUN THE APP