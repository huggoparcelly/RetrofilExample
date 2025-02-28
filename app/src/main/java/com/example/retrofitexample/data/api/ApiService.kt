package com.example.retrofitexample.data.api

import com.example.retrofitexample.data.models.Comment
import com.example.retrofitexample.data.models.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/posts/{postId}/comments")
    suspend fun getCommentsByPostId(@Path("postId") postId:Int): Response<List<Comment>>
}