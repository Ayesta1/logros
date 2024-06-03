package com.example.logros.dataClasses

import com.google.gson.annotations.SerializedName

data class UserAchievement(
    @SerializedName("achievementid") val achievementid: String,
    @SerializedName("userid") val userid: String,
    @SerializedName("completationDate") val completationDate: String
)
