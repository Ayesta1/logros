package com.example.logros.dataClasses

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username") val username: String,
    @SerializedName("password") val password: String,
    @SerializedName("birthday") val birthday: String,
    @SerializedName("mail") val mail: String
)