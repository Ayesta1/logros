package com.example.logros

import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var completeButton: Button
    private lateinit var apiService: ApiService
    private lateinit var sharedPreferences: SharedPreferences
    private var achievementId: String? = null
    private var userId: String? = null
    private var id: String? = null
    private var isCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.achievement_detail)

        titleTextView = findViewById(R.id.titleTextView)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        completeButton = findViewById(R.id.completeButton)

        sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("user_id", null)
        achievementId = intent.getStringExtra("ACHIEVEMENT_ID")

        if (achievementId == null || userId == null) {
            Toast.makeText(this, "Error al cargar el logro", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        completeButton.setOnClickListener {
            toggleCompletionStatus()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.15:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        loadAchievementDetails()
//        checkUserAchievementStatus()
//
//        completeButton.setOnClickListener {
//            if (id == null) {
//                completeAchievement()
//            }
//        }
    }
    private fun toggleCompletionStatus() {
        isCompleted = !isCompleted
        if (isCompleted) {
            completeButton.text = "Completado"
            completeButton.setBackgroundColor(getColor(android.R.color.holo_green_light))
        } else {
            completeButton.text = "No completado"
            completeButton.setBackgroundColor(getColor(android.R.color.holo_red_light))
        }
    }

    private fun loadAchievementDetails() {
        val title = intent.getStringExtra("TITLE")
        val description = intent.getStringExtra("DESCRIPTION")

        titleTextView.text = title
        descriptionTextView.text = description
    }

//    private fun checkUserAchievementStatus() {
//        val callUserAchievements = apiService.getUserAchievements()
//        callUserAchievements.enqueue(object : Callback<List<UserAchievement>> {
//            override fun onResponse(call: Call<List<UserAchievement>>, response: Response<List<UserAchievement>>) {
//                if (response.isSuccessful && response.body() != null) {
//                    val userAchievements = response.body()!!
//                    val userAchievement = userAchievements.find {
//                        it.userid == userId && it.achievementid == achievementId
//                    }
//
//                    if (userAchievement != null) {
//                        id = userAchievement.id
//                        completeButton.text = "Completado"
//                        completeButton.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
//                    } else {
//                        completeButton.text = "No Completado"
//                        completeButton.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
//                    }
//                } else {
//                    Toast.makeText(this@AchievementDetailActivity, "Error al comprobar el estado del logro", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<List<UserAchievement>>, t: Throwable) {
//                Toast.makeText(this@AchievementDetailActivity, "Error en la solicitud: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//
//    private fun completeAchievement() {
//        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
//
//        val userAchievement = UserAchievement(
//            id = "", // Suponiendo que userAchievementId no es nulo
//            userid = userId!!, // Suponiendo que userId no es nulo
//            achievementid = achievementId!!, // Suponiendo que achievementId no es nulo
//            completationdate = currentDate
//        )
//
//        val call = apiService.updateUserAchievement(userAchievement.id, userAchievement)
//        call.enqueue(object : Callback<UserAchievement> {
//            override fun onResponse(call: Call<UserAchievement>, response: Response<UserAchievement>) {
//                if (response.isSuccessful) {
//                    val updatedUserAchievement = response.body()
//                    // Aquí deberías manejar la respuesta del servidor y actualizar la interfaz de usuario según sea necesario
//                    // Por ejemplo, mostrar un mensaje de éxito, cambiar el estado del botón, etc.
//                    Toast.makeText(this@AchievementDetailActivity, "Logro completado", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this@AchievementDetailActivity, "Error al completar el logro", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<UserAchievement>, t: Throwable) {
//                Toast.makeText(this@AchievementDetailActivity, "Error en la solicitud: ${t.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}
