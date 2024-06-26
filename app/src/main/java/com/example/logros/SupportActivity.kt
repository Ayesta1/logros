package com.example.logros

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SupportActivity : AppCompatActivity() {

    private lateinit var titleTextView: TextView
    private lateinit var supportTextView: TextView
    private lateinit var contactButton: Button
    private lateinit var queEsButton: Button
    private var contactPressed = false
    private var queEsPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.support_detail)
        titleTextView = findViewById(R.id.titleTextView)
        supportTextView = findViewById(R.id.supportTextView)
        queEsButton = findViewById(R.id.queEsButton)
        contactButton = findViewById(R.id.contactButton)

        // FOOTER
        val profileButton: Button = findViewById(R.id.profileButton)
        val supportButton: Button = findViewById(R.id.supportButton)
        val homeButton: Button = findViewById(R.id.homeButton)
        supportButton.setOnClickListener {
            val intent = Intent(this, SupportActivity::class.java)
            startActivity(intent)
        }

        profileButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        homeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // FOOTER

        queEsButton.setOnClickListener {
            toggleQueEsStatus()
        }
        contactButton.setOnClickListener {
            toggleContactStatus()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun toggleQueEsStatus() {
        queEsPressed = !queEsPressed
        if (queEsPressed) {
            if (contactPressed) {
                toggleContactStatus()
            }
            supportTextView.text = "Bitácora es una aplicación que contiene un sinfín de logros de todo tipo para completar en la vida real, desde logros más cotidianos y generales a otros más específicos y concretos. ¡Es entretenimiento en estado puro, sobretodo para entretenerse en tiempos muertos!"
            queEsButton.setBackgroundColor(getColor(android.R.color.holo_green_light))
        } else {
            supportTextView.text = ""
            queEsButton.setBackgroundColor(getColor(android.R.color.holo_blue_light))
        }
    }
    @SuppressLint("SetTextI18n")
    private fun toggleContactStatus() {
        contactPressed = !contactPressed
        if (contactPressed) {
            if (queEsPressed) {
                toggleQueEsStatus()
            }
            supportTextView.text = "Jordi Ayesta \n(ayesta1@gmail.com) \n\nRoc Rovira \n(roviraroc@gmail.com) \n\nAli Youssef \n(ayoussefmohammad@gmail.com)"
            contactButton.setBackgroundColor(getColor(android.R.color.holo_green_light))
        } else {
            supportTextView.text = ""
            contactButton.setBackgroundColor(getColor(android.R.color.holo_blue_light))
        }
    }
}
