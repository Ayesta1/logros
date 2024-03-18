package com.example.logros

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridLayout
import com.example.logros.DataClasses.Categories
import com.example.logros.retrofit.ApiService
import com.example.logros.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    private val service = RetrofitClient.retrofit.create(ApiService::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.campo_logros)
        var call = service.getCategory()
        call.enqueue(object : Callback<List<Categories>>{
            override fun onResponse(
                call: Call<List<Categories>>,
                response: Response<List<Categories>>
            ){
                if (response.isSuccessful){
                    val respuesta = response.body()
                    Log.e("respuesta", response.body().toString())

                }

            }

            override fun onFailure(call: Call<List<Categories>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })}}

