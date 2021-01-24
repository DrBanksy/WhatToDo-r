package com.herd.whattodo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.herd.whattodo.R
import com.herd.whattodo.model.Category

class CategoryAdapter(val context: Context, val categories: List<Category>, val itemClicked: (Category) -> Unit ) : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>(){

    inner class CategoryHolder(itemView: View, val itemClicked: (Category) -> Unit) : RecyclerView.ViewHolder(itemView) {
        //we need to find the viewholders and assign them to variables
        val categoryImage:ImageView = itemView.findViewById(R.id.categoryImage)
        val categoryName:TextView =itemView.findViewById(R.id.categoryName)

        fun bindCategory(category: Category, context: Context) {
            val resourceId =context.resources.getIdentifier(category.image, "drawable", context.packageName)
            categoryImage.setImageResource(resourceId)
            categoryName.text = category.name
            itemView.setOnClickListener { itemClicked(category) }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.category_cell, parent, false)
        return CategoryHolder(view, itemClicked)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bindCategory(categories[position], context)
    }

}