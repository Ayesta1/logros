package com.example.logros

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logros.dataClasses.UserAchievement
import com.example.logros.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class AchievementDetailActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var statusButton: Button
    private lateinit var apiService: ApiService

    private lateinit var achievementid: String
    private lateinit var userid: String

    private var isCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.achievement_detail)

        titleTextView = findViewById(R.id.titleTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        statusButton = findViewById(R.id.statusButton)

        // Obtener los datos del intent
        achievementid = intent.getStringExtra("achievementid") ?: ""
        userid = intent.getStringExtra("userid") ?: ""
        val title = intent.getStringExtra("title") ?: ""
        val description = intent.getStringExtra("description") ?: ""

        titleTextView.text = title
        descriptionTextView.text = description

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.118.3.35:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Verificar si el logro está completado
        checkIfAchievementCompleted()

        statusButton.setOnClickListener {
            if (isCompleted) {
                markAsNotCompleted()
            } else {
                markAsCompleted()
            }
        }
    }

    private fun checkIfAchievementCompleted() {
        apiService.checkUserAchievement(userid, achievementid).enqueue(object : Callback<UserAchievement?> {
            override fun onResponse(call: Call<UserAchievement?>, response: Response<UserAchievement?>) {
                if (response.isSuccessful) {
                    isCompleted = response.body() != null
                    updateButton()
                } else if (response.code() == 404) {
                    isCompleted = false
                    updateButton()
                } else {
                    Toast.makeText(this@AchievementDetailActivity, "Error al verificar el estado del logro", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserAchievement?>, t: Throwable) {
                Toast.makeText(this@AchievementDetailActivity, "Error en la solicitud: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun markAsCompleted() {
        val currentDate = getCurrentDate()
        val userAchievement = UserAchievement(achievementid, userid, currentDate)

        apiService.addUserAchievement(userAchievement).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    isCompleted = true
                    updateButton()
                } else {
                    Toast.makeText(this@AchievementDetailActivity, "Error al marcar como completado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@AchievementDetailActivity, "Error en la solicitud: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun markAsNotCompleted() {
        apiService.removeUserAchievement(achievementid, userid).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    isCompleted = false
                    updateButton()
                } else {
                    Toast.makeText(this@AchievementDetailActivity, "Error al marcar como no completado", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@AchievementDetailActivity, "Error en la solicitud: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateButton() {
        if (isCompleted) {
            statusButton.text = "Completado"
            statusButton.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
        } else {
            statusButton.text = "No Completado"
            statusButton.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
