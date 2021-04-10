package com.example.firestorereaddata

class User(val firstname: String, val lastname: String, val birthplace: String){
    constructor(): this("","","")
    //Empty constructor needed for firestore
}