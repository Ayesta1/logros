package com.example.logros.retrofit

import com.example.logros.DataClasses.Categories
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("category")
    fun getCategory(): Call<List<Categories>>


}