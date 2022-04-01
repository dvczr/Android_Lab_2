package com.dvczr.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/* TODO READ THE README FILE FIRST - INCOMPLETE - */

class MainActivity : AppCompatActivity() {

    private val recyclerItems = ArrayList<String>()
    private lateinit var customAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("\n\t*****************************\n\t* LOADED ACTIVITY... \"Main\" *\n\t-----------------------------")

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        customAdapter = CustomAdapter(recyclerItems)
        val layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter



        val user = intent.getParcelableExtra<User>("user_inactive")

        if (user != null) {
           val adieuMsg = findViewById<TextView>(R.id.text_home)
            adieuMsg.text = user.lastName
            recyclerViewItems()
        }

        val btnToLogin = findViewById<Button>(R.id.btn_to_login)
        btnToLogin.setOnClickListener {
            println("\t * - BUTTON ACTION CALLED -\n\t   => \"Go To Login\"")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val btnCreateNewAccount = findViewById<Button>(R.id.btn_new_user)
        btnCreateNewAccount.setOnClickListener {
            println("\t * - BUTTON ACTION CALLED -\n\t   => \"Create New Account\"")
            val intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)
        }

       /* TODO("Create Intent to navigate") - DONE - */
    }

    private fun recyclerViewItems() {
        recyclerItems.add("¡")
        recyclerItems.add("Interesting")
        recyclerItems.add("Fact")
        recyclerItems.add("!")
        recyclerItems.add("Monkey")
        recyclerItems.add("Eat")
        recyclerItems.add("Bananas")
        recyclerItems.add("Like")
        recyclerItems.add("Crazy")
        recyclerItems.add("...")
        recyclerItems.add("Weird")
        recyclerItems.add("Right?")

    }
}