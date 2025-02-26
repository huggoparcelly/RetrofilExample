package com.example.retrofitexample.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample.R

import com.example.retrofitexample.data.models.Comment
import com.google.android.material.textview.MaterialTextView

class CommentAdapter(private val comments: List<Comment>) :
    RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: MaterialTextView = view.findViewById(R.id.item_comment_name)
        val email: MaterialTextView = view.findViewById(R.id.item_comment_email)
        val body: MaterialTextView = view.findViewById(R.id.item_comment_body)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CommentViewHolder,
        position: Int
    ) {
        holder.name.text = comments[position].name
        holder.email.text = comments[position].email
        holder.body.text = comments[position].body
    }

    override fun getItemCount(): Int = comments.size


}