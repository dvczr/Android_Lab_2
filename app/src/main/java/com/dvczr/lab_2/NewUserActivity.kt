package com.dvczr.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import android.os.Parcel
import android.os.Parcelable


class NewUserActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user)

        println("\n\t********************************\n\t* LOADED ACTIVITY... \"NewUser\" *\n\t--------------------------------")

        val firstNameNew = findViewById<EditText>(R.id.first_name_new)
        val lastNameNew = findViewById<EditText>(R.id.last_name_new)
        val emailNew = findViewById<EditText>(R.id.email_new)
        val passwordNew = findViewById<EditText>(R.id.password_new)
        val passwordConfirm = findViewById<EditText>(R.id.password_confirm)

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val btnOk = findViewById<Button>(R.id.btn_ok)
        btnOk.setOnClickListener {
            println("\t * - BUTTON ACTION CALLED -\n\t   => \"OK\"")

            // Looks for empty EditText-input by Running isNotEmpty() on EditText.text
            if (firstNameNew.text.isNotEmpty() && lastNameNew.text.isNotEmpty() && emailNew.text.isNotEmpty() &&
                passwordNew.text.isNotEmpty() && passwordConfirm.text.isNotEmpty()) {

                    // Password confirmation
                    if (passwordNew.text.toString() == passwordConfirm.text.toString()) {

                        // Instantiate new User() & value initialization
                        val user = User(
                            firstNameNew.text.toString(),
                            lastNameNew.text.toString(),
                            emailNew.text.toString(),
                            passwordNew.text.toString())

                        user.firstName = firstNameNew.text.toString()
                        user.lastName = lastNameNew.text.toString()
                        user.email = emailNew.text.toString()
                        user.password = passwordNew.text.toString()


                        println("\t   * NEW USER ACCOUNT CREATED *\n" +
                                "\t     ------------------------\n")
                        println(
                            "\t\t * USER *\n" +
                                    "\t\t   ----\n" +
                                    "\t\tFirstname: ${user.firstName}\n" +
                                    "\t\tLastname: ${user.lastName}\n" +
                                    "\t\tE-mail: ${user.email}\n" +
                                    "\t\tPassword: ${user.password}\n"
                        )

                        val intent = Intent(this, LoginActivity::class.java)
                        intent.putExtra("user_new", user.also { println("\n - This message was brought to you partly by .also -") }.apply { user.lastName = "Adieu, Farewell ${user.firstName}!"})
                        startActivity(intent)

                    } else {
                        passwordNew.text = null
                        passwordConfirm.text = null
                        Toast.makeText(this, "Your password do not match", Toast.LENGTH_LONG).show()
                    }
            } else {
                Toast.makeText(this, "You need to fill all boxes", Toast.LENGTH_LONG).show()
            }
        }

        val btnCancel = findViewById<Button>(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            println("\t * - BUTTON ACTION CALLED -\n\t   => \"Cancel\"")
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
