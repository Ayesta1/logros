package com.example.logros.retrofit

import com.example.logros.dataClasses.Achievement
import com.example.logros.dataClasses.Category
import com.example.logros.dataClasses.User
import com.example.logros.dataClasses.UserAchievement
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("category")
    fun getCategories(): Call<List<Category>>

    @GET("achievements")
    fun getAchievements(): Call<List<Achievement>>

    @GET("users")
    fun getUsers(): Call<List<User>>

    @POST("users")
    fun createUser(@Body user: User): Call<User>

    @POST("userachievements")
    fun addUserAchievement(@Body userAchievement: UserAchievement): Call<Void>

    @DELETE("userachievements/{achievementid}/{userid}")
    fun removeUserAchievement(
        @Path("achievementid") achievementid: String,
        @Path("userid") userid: String
    ): Call<Void>

    @GET("userachievements/{userid}/{achievementid}")
    fun checkUserAchievement(
        @Path("userid") userid: String,
        @Path("achievementid") achievementid: String
    ): Call<UserAchievement?>
}