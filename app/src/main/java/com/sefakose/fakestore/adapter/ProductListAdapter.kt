package com.sefakose.fakestore.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.sefakose.fakestore.R
import com.sefakose.fakestore.databinding.ListItemProductBinding
import com.sefakose.fakestore.databinding.ListItemProductGridBinding
import com.sefakose.fakestore.model.Product

class ProductListAdapter(var productList: ArrayList<Product>, private var onClick: (position:Int)->Unit) :
    Adapter<ProductListAdapter.ProductListViewHolder>() {
    class ProductListViewHolder(val view: ListItemProductBinding) : ViewHolder(view.root) {
        fun bind(product: Product) {
            view.apply {
                tvTitle.text = product.title
                tvPrice.text = "$".plus(product.price)
                Glide.with(root).load(product.image).into(imagevProduct)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListViewHolder {
        val binding = DataBindingUtil.inflate<ListItemProductBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item_product,
            parent,
            false
        )
        return ProductListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductListViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.view.root.setOnClickListener { onClick(position) }
    }
}

