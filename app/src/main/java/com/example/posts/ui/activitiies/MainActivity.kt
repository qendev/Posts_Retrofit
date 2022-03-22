package com.example.posts.ui.activitiies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.posts.R
import com.example.posts.databinding.ActivityMainBinding
import com.example.posts.models.MyData
import com.example.posts.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getMyPosts()
    }

    private fun getMyPosts() {
        //create a retrofit builder
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getPosts()

        retrofitData.enqueue(object : Callback<List<MyData.MyDataItem>?> {
            override fun onResponse(call: Call<List<MyData.MyDataItem>?>, response: Response<List<MyData.MyDataItem>?>) {
                val responseBody = response.body()!!
                val myStringbuilder = StringBuilder()
                for (myData in responseBody){
                    myStringbuilder.append(myData.id)
                    myStringbuilder.append("\n")
                }
                binding.textViewId.text = myStringbuilder
                Log.e("TAG","$myStringbuilder")
            }

            override fun onFailure(call: Call<List<MyData.MyDataItem>?>, t: Throwable) {
                Log.d("MainActivity","onFailure:"+t.message)
            }
        })
    }
}