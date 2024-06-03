package com.example.logros.dataClasses

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("email") val email: String
)