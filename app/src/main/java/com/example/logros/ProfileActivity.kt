package com.example.logros

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.logros.R
import com.example.logros.dataClasses.BioUpdateRequest
import com.example.logros.dataClasses.User
import com.example.logros.retrofit.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileActivity : AppCompatActivity() {

    private lateinit var usernameTextView: TextView
    private lateinit var biographyTextMultiLine: EditText
    private lateinit var deleteAccountButton: Button
    private lateinit var saveButton: Button
    private lateinit var username: String
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        sharedPreferences = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        username = sharedPreferences.getString("username", null).toString()
        usernameTextView = findViewById(R.id.usernameTextView)
        biographyTextMultiLine = findViewById(R.id.biographyTextMultiLine)
        deleteAccountButton = findViewById(R.id.deleteAccountButton)
        saveButton = findViewById(R.id.saveButton)

        usernameTextView.text = username

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.15:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        saveButton.setOnClickListener {
            val newBiography = biographyTextMultiLine.text.toString()
            updateUserBiography(newBiography)
        }
    }

    private fun updateUserBiography(newBiography: String) {
        val userId = sharedPreferences.getString("userId", null) ?: ""
        println("Updating biography for user id: $userId with biography: $newBiography")

        val bioUpdateRequest = BioUpdateRequest(newBiography)

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.updateUserBio(userId, bioUpdateRequest)

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(this@ProfileActivity, "Biografía actualizada correctamente", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@ProfileActivity, "Error al actualizar la biografía", Toast.LENGTH_SHORT).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@ProfileActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}