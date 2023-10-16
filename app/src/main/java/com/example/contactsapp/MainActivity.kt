package com.example.contactsapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var username:EditText
    lateinit var phone:EditText
    lateinit var description:EditText
    lateinit var btn:Button

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ContactAdapter
    lateinit var contactList: MutableList<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username=findViewById(R.id.editTname)
        phone=findViewById(R.id.editTphone)
        description=findViewById(R.id.editTdescription)
        btn=findViewById(R.id.btn_save)
        recyclerView=findViewById(R.id.recyclerviewContact)

        contactList=ArrayList()
        adapter= ContactAdapter(contactList)
        recyclerView.adapter=adapter

        btn.setOnClickListener(View.OnClickListener {
            var name=username.text.toString().trim()
            var phone=phone.text.toString().trim()
            var description=description.text.toString().trim()

            if(name.length<3){
                var toast= Toast.makeText(this,"name should be 3 characters or more",Toast.LENGTH_LONG)
                toast.show()
            }
            else if(phone.length!=11){
                var toast= Toast.makeText(this,"phone number should be 11 digits",Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                val newContact=Contact(name,phone,description)
                contactList.add(newContact)
                adapter.notifyDataSetChanged()
                ClearInputFields()

            }
        })
    adapter.onProfileClickListener=object :ContactAdapter.OnItemClickListener{
        override fun onItemClick(position: Int, items: Contact) {
            var intent= Intent(this@MainActivity,DetailsActivity::class.java)
            intent.putExtra("name",items.name)
            intent.putExtra("phone",items.phone)
            startActivity(intent)
        }

    }
        adapter.onCallClickListener=object :ContactAdapter.OnItemClickListener{
            override fun onItemClick(position: Int, items: Contact) {
                var dialIntent= Intent(Intent.ACTION_DIAL)
                dialIntent.data= Uri.parse("tel :"+items.phone)
                startActivity(dialIntent)
            }

        }
    }



    fun ClearInputFields(){
        username.setText("")
        phone.setText("")
        description.setText("")

    }
}