package com.example.logros

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.logros.retrofit.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SupportActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var supportTextView: TextView
    private lateinit var contactButton: Button
    private lateinit var queEsButton: Button
    private lateinit var apiService: ApiService
    private lateinit var sharedPreferences: SharedPreferences
    private var achievementId: String? = null
    private var userId: String? = null
    private var isCompleted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.support_detail)
        titleTextView = findViewById(R.id.titleTextView)
        supportTextView = findViewById(R.id.supportTextView)
        queEsButton = findViewById(R.id.queEsButton)
        contactButton = findViewById(R.id.contactButton)

        sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        userId = sharedPreferences.getString("user_id", null)
        achievementId = intent.getStringExtra("ACHIEVEMENT_ID")

        queEsButton.setOnClickListener {
            toggleQueEsStatus()
        }

        contactButton.setOnClickListener {
            toggleContactStatus()
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
    private fun toggleQueEsStatus() {
        isCompleted = !isCompleted
        if (isCompleted) {
            supportTextView.text = "Bitácora es una aplicación que contiene un sinfín de logros de todo tipo para completar en la vida real, desde logros más cotidianos y generales a otros más específicos y concretos. ¡Es entretenimiento en estado puro, sobretodo para entretenerse en tiempos muertos!"
            queEsButton.setBackgroundColor(getColor(android.R.color.holo_green_light))
        } else {
            supportTextView.text = "Si tienes algún problema con nuestra aplicación puede contactar con nuestro equipo de soporte (soporte@bitacora.com) o puede contactar con los desarrolladores directamente a nuestros correos personales."
            queEsButton.setBackgroundColor(getColor(android.R.color.holo_blue_light))
        }
    }

    private fun toggleContactStatus() {
        isCompleted = !isCompleted
        if (isCompleted) {
            supportTextView.text = "Jordi Ayesta \n(ayesta1@gmail.com) \n\nRoc Rovira \n(roviraroc@gmail.com) \n\nAli Youssef \n(ayoussefmohammad@gmail.com)"
            contactButton.setBackgroundColor(getColor(android.R.color.holo_green_light))
        } else {
            supportTextView.text = "Si tienes algún problema con nuestra aplicación puede contactar con nuestro equipo de soporte (soporte@bitacora.com) o puede contactar con los desarrolladores directamente a nuestros correos personales."
            contactButton.setBackgroundColor(getColor(android.R.color.holo_blue_light))
        }
    }

    private fun loadAchievementDetails() {
        val title = intent.getStringExtra("TITLE")

        titleTextView.text = title
        supportTextView.text = "Si tienes algún problema con nuestra aplicación puede contactar con nuestro equipo de soporte (soporte@bitacora.com) o puede contactar con los desarrolladores directamente a nuestros correos personales."
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
