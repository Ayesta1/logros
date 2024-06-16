package com.example.logros.retrofit

import com.example.logros.dataClasses.Achievement
import com.example.logros.dataClasses.BioUpdateRequest
import com.example.logros.dataClasses.Category
import com.example.logros.dataClasses.User
import com.example.logros.dataClasses.UserAchievement
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("userachievement")
    fun getUserAchievements(): Call<List<UserAchievement>>

    @PUT("userachievement/{id}")
    fun updateUserAchievement(@Path("id") id: String, @Body userAchievement: UserAchievement): Call<UserAchievement>

    @POST("userachievement")
    fun createUserAchievement(@Body userAchievement: UserAchievement): Call<UserAchievement>
    @GET("users/{username}")
    fun getUserByUsername(@Path("username") username: String): Call<User>

    @PUT("/users/{id}/biography")
    suspend fun updateUserBio(@Path("id") userId: String, @Body bioUpdateRequest: BioUpdateRequest): Response<User>
}