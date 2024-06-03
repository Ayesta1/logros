package com.example.logros

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logros.AchievementDetailActivity
import com.example.logros.R
import com.example.logros.dataClasses.Achievement
import com.example.logros.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AchievementsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        val categoryId = intent.getStringExtra("CATEGORY_ID")?.toIntOrNull()

        if (categoryId == null) {
            Toast.makeText(this, "ID de categoría no válido", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        val buttons = listOf(
            findViewById<Button>(R.id.achievementButton1),
            findViewById<Button>(R.id.achievementButton2),
            findViewById<Button>(R.id.achievementButton3),
            findViewById<Button>(R.id.achievementButton4),
            findViewById<Button>(R.id.achievementButton5),
            findViewById<Button>(R.id.achievementButton6),
            findViewById<Button>(R.id.achievementButton7),
            findViewById<Button>(R.id.achievementButton8),
            findViewById<Button>(R.id.achievementButton9),
            findViewById<Button>(R.id.achievementButton10),
            findViewById<Button>(R.id.achievementButton11)
        )

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.118.3.35:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val callAchievements = apiService.getAchievements()
        callAchievements.enqueue(object : Callback<List<Achievement>> {
            override fun onResponse(call: Call<List<Achievement>>, response: Response<List<Achievement>>) {
                if (response.isSuccessful && response.body() != null) {
                    val achievements = response.body()!!.filter { it.categoryId == categoryId }

                    // Testing
                    if (achievements.size < 9) {
                        Toast.makeText(this@AchievementsActivity, "No hay suficientes logros para esta categoría", Toast.LENGTH_SHORT).show()
                        finish()
                        return
                    }

                    // Testing
                    if (achievements.size > 11) {
                        Toast.makeText(this@AchievementsActivity, "Existen " + achievements.size + " logros", Toast.LENGTH_SHORT).show()
                    }

                    achievements.take(11).forEachIndexed { index, achievement ->
                        buttons[index].text = achievement.title
                        buttons[index].setOnClickListener {
                            val intent = Intent(this@AchievementsActivity, AchievementDetailActivity::class.java)
                            intent.putExtra("TITLE", achievement.title)
                            intent.putExtra("DESCRIPTION", achievement.description)
                            startActivity(intent)
                        }
                    }
                } else {
                    Toast.makeText(this@AchievementsActivity, "Error en la respuesta", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Achievement>>, t: Throwable) {
                Toast.makeText(this@AchievementsActivity, "Error en la solicitud: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
