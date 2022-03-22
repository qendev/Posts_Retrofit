package com.example.posts.network

import com.example.posts.models.MyData
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("posts")
    fun getPosts():Call<List<MyData.MyDataItem>>
}