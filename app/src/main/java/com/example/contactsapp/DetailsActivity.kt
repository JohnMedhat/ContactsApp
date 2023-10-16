package com.example.contactsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    lateinit var username:TextView
    lateinit var phone:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        username=findViewById(R.id.tv_nameDetails)
        phone=findViewById(R.id.tv_phoneDetails)

        var strUser: String?=intent.getStringExtra("name")
        var strPhone: String?=intent.getStringExtra("phone")

        username.setText(strUser)
        phone.setText(strPhone)
    }
}