package com.example.retrofitexample

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample.data.api.ApiServiceClient
import com.example.retrofitexample.ui.adapters.CommentAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val apiService = ApiServiceClient.instance

    private val mMenuSelection: AutoCompleteTextView by lazy { findViewById(R.id.menu_selection) }
    private val mCommentList: RecyclerView by lazy { findViewById(R.id.cimment_list) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMenuSelection.setOnItemClickListener{ parent, view, position, id ->

            val postTitle = parent.getItemAtPosition(position) as String
            val postId = postTitle.split("-")
                .first()
                .trim()
                .toInt()

            CoroutineScope(Dispatchers.IO).launch {

                val commentsResponse = apiService.getCommentsByPostId(postId)

                val comments = commentsResponse.body().orEmpty()
                val adapter = CommentAdapter(comments)

                withContext(Dispatchers.Main) {
                    mCommentList.adapter = adapter
                    mCommentList.layoutManager = LinearLayoutManager(baseContext)
                    adapter.notifyDataSetChanged()
                }

            }
        }



    }

    override fun onStart() {
        super.onStart()

        CoroutineScope(Dispatchers.IO).launch {
            val postResponse = apiService.getPosts()

            val posts = postResponse.body()
            val postTitles = posts?.map { "${it.id} - ${it.title}" }?.toList().orEmpty()
            val adapter = ArrayAdapter(baseContext, android.R.layout.simple_list_item_1, postTitles)
            withContext(Dispatchers.Main) {
                mMenuSelection.setAdapter(adapter)
            }
        }

    }
}