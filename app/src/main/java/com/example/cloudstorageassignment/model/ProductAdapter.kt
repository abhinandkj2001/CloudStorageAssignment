package com.example.cloudstorageassignment.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cloudstorageassignment.R

class ProductAdapter(private val pd_list:ArrayList<product_items>):RecyclerView.Adapter<ProductAdapter.ViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_items,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProductAdapter.ViewHolder, position: Int) {
        val  pdcts:product_items=pd_list[position]
        holder.id_view.text=pdcts.productId
        holder.name_view.text=pdcts.productName
        holder.age.text=pdcts.productPrice.toString()
    }

    override fun getItemCount(): Int {
        return pd_list.size
    }
    public class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val id_view :TextView = itemView.findViewById(R.id.textViewProductId)
        val name_view:TextView = itemView.findViewById(R.id.textViewProductName)
        val age : TextView = itemView.findViewById(R.id.textViewProductPrice)

    }
}
