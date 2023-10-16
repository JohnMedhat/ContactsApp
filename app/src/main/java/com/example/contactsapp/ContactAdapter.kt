package com.example.contactsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(var items:List<Contact>): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder (itemView){
        var userName:TextView = itemView.findViewById(R.id.tv_name);
        var phone:TextView = itemView.findViewById(R.id.tv_phone);
        var imageViewProfile:ImageView = itemView.findViewById(R.id.img_profile);
        var imageViewCall:ImageView = itemView.findViewById(R.id.img_call);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view:View = LayoutInflater.from(parent.context).inflate(R.layout.itemcontact,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=items[position]
            holder.userName.text = item.name
            holder.phone.text = item.phone

        if(onProfileClickListener!=null){

            holder.imageViewProfile.setOnClickListener{
                onProfileClickListener?.onItemClick(position,item)
            }
        }
        if(onCallClickListener!=null){

            holder.imageViewCall.setOnClickListener{
                onCallClickListener?.onItemClick(position,item)
            }
        }
    }

    var onCallClickListener:OnItemClickListener?=null
    var onProfileClickListener:OnItemClickListener?=null

    interface OnItemClickListener{

        fun onItemClick (position: Int,items:Contact)
    }

}