package com.example.posts.models

class MyData : ArrayList<MyData.MyDataItem>(){
    data class MyDataItem(
        val body: String,
        val id: Int,
        val title: String,
        val userId: Int
    )
}