package com.example.firestorereaddata

import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.user_row.view.*


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