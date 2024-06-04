package com.example.logros

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logros.dataClasses.Achievement
import com.example.logros.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AchievementsActivity : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var button10: Button
    private lateinit var button11: Button
    private lateinit var button12: Button
    private lateinit var button13: Button
    private lateinit var button14: Button
    private lateinit var button15: Button
    private lateinit var button16: Button
    private lateinit var button17: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)

        button1 = findViewById(R.id.achievementButton1)
        button2 = findViewById(R.id.achievementButton2)
        button3 = findViewById(R.id.achievementButton3)
        button4 = findViewById(R.id.achievementButton4)
        button5 = findViewById(R.id.achievementButton5)
        button6 = findViewById(R.id.achievementButton6)
        button7 = findViewById(R.id.achievementButton7)
        button8 = findViewById(R.id.achievementButton8)
        button9 = findViewById(R.id.achievementButton9)
        button10 = findViewById(R.id.achievementButton10)
        button11 = findViewById(R.id.achievementButton11)
        button12 = findViewById(R.id.achievementButton12)
        button13 = findViewById(R.id.achievementButton13)
        button14 = findViewById(R.id.achievementButton14)
        button15 = findViewById(R.id.achievementButton15)
        button16 = findViewById(R.id.achievementButton16)
        button17 = findViewById(R.id.achievementButton17)

        val categoryId = intent.getStringExtra("CATEGORY_ID")?.toInt() ?: return
        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.15:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val apiService = retrofit.create(ApiService::class.java)

        val callAchievements = apiService.getAchievements()
        callAchievements.enqueue(object : Callback<List<Achievement>> {
            override fun onResponse(call: Call<List<Achievement>>, response: Response<List<Achievement>>) {
                if (response.isSuccessful && response.body() != null) {
                    val achievements = response.body()!!.filter { it.categoryId == categoryId }

                    if (achievements.size >= 10) {
                        button1.text = achievements[0].title
                        button2.text = achievements[1].title
                        button3.text = achievements[2].title
                        button4.text = achievements[3].title
                        button5.text = achievements[4].title
                        button6.text = achievements[5].title
                        button7.text = achievements[6].title
                        button8.text = achievements[7].title
                        button9.text = achievements[8].title
                        button10.text = achievements[9].title
                        button11.text = achievements[10].title
                        button12.text = achievements[11].title
                        button13.text = achievements[12].title
                        button14.text = achievements[13].title
                        button15.text = achievements[14].title
                        button16.text = achievements[15].title
                        button17.text = achievements[16].title

                        button1.setOnClickListener {
                            navigateToAchievementDetail(achievements[0])
                        }

                        button2.setOnClickListener {
                            navigateToAchievementDetail(achievements[1])
                        }

                        button3.setOnClickListener {
                            navigateToAchievementDetail(achievements[2])
                        }

                        button4.setOnClickListener {
                            navigateToAchievementDetail(achievements[3])
                        }

                        button5.setOnClickListener {
                            navigateToAchievementDetail(achievements[4])
                        }

                        button6.setOnClickListener {
                            navigateToAchievementDetail(achievements[5])
                        }

                        button7.setOnClickListener {
                            navigateToAchievementDetail(achievements[6])
                        }

                        button8.setOnClickListener {
                            navigateToAchievementDetail(achievements[7])
                        }

                        button9.setOnClickListener {
                            navigateToAchievementDetail(achievements[8])
                        }

                        button10.setOnClickListener {
                            navigateToAchievementDetail(achievements[9])
                        }

                        button11.setOnClickListener {
                            navigateToAchievementDetail(achievements[10])
                        }

                        button12.setOnClickListener {
                            navigateToAchievementDetail(achievements[11])
                        }

                        button13.setOnClickListener {
                            navigateToAchievementDetail(achievements[12])
                        }

                        button14.setOnClickListener {
                            navigateToAchievementDetail(achievements[13])
                        }

                        button15.setOnClickListener {
                            navigateToAchievementDetail(achievements[14])
                        }

                        button16.setOnClickListener {
                            navigateToAchievementDetail(achievements[15])
                        }

                        button17.setOnClickListener {
                            navigateToAchievementDetail(achievements[16])
                        }
                    } else {
                        Toast.makeText(this@AchievementsActivity, "No hay suficientes logros", Toast.LENGTH_SHORT).show()
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

    private fun navigateToAchievementDetail(achievement: Achievement) {
        val intent = Intent(this, AchievementDetailActivity::class.java).apply {
            putExtra("ACHIEVEMENT_ID", achievement.id)
            putExtra("TITLE", achievement.title)
            putExtra("DESCRIPTION", achievement.description)
        }
        startActivity(intent)
    }
}