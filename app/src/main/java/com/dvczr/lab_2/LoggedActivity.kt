package com.dvczr.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.os.Parcel
import android.os.Parcelable


class LoggedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logged)

       println("\n\t*******************************\n\t* LOADED ACTIVITY... \"Logged\" *\n\t-------------------------------")

        val user = intent.getParcelableExtra<User>("user_active")

        val btnLogOut = findViewById<Button>(R.id.btn_log_out)
        btnLogOut.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("user_inactive",
                user.also { println("This message was brought to you by .also") }
                    .apply { this!!.lastName = "Adieu & Farewell! ${user!!.firstName}!"})
            startActivity(intent)
        }
    }
}