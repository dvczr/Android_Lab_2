@file:JvmName("KeyboardUtils")


package com.dvczr.lab_2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text


class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /* LOGCAT PURPOSE ONLY */
        println("\n\t******************************\n\t* LOADED ACTIVITY... \"Login\" *\n\t------------------------------")

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]
        lateinit var user: User
        var i: Int
        var iTemp: Int
        var userList = viewModel.userList
        val userNew = intent.getParcelableExtra<User>("user_new")
        val password = findViewById<EditText>(R.id.password)
        val email = findViewById<EditText>(R.id.email)
        val btnLogin = findViewById<Button>(R.id.btn_login)
        val btnBack = findViewById<Button>(R.id.btn_back)
        val btnNewUser = findViewById<TextView>(R.id.clickable_new_user)

        /* PUT.EXTRA() PARCELABLE HANDLER - USER() CLASS - */
        if (userNew != null) {
            user = User(userNew.firstName, userNew.lastName, userNew.email, userNew.password)
            viewModel.userList.add(user)
            email.setText(userNew.email)
            password.setText(userNew.password)
        }

        /*-----------------------------*/
        /* MAIN ACTIVITY'S BUTTON LOGIC*/
        /*-----------------------------*/
        /* BUTTON: LOGIN */
        btnLogin.setOnClickListener {
            i = 0
            iTemp = -1
            for (users in userList) {
                iTemp ++
                if ((email.text.toString() == users.email) && (password.text.toString() == users.password)) {
                    i = iTemp

                }
            }
            if (i > -1 && (email.text.toString() == userList[i].email && password.text.toString() == userList[i].password)) {
                println("\t * - BUTTON ACTION WAS SUCCESSFUL - \n\t   => \"Login\"")
                Toast.makeText(this, "Welcome back ${userList[i].firstName}", Toast.LENGTH_LONG).show()

                val intent = Intent(this, LoggedActivity::class.java)
                intent.putExtra("user_active", userList[i])
                startActivity(intent)
            } else if (email.text.toString() != userList[i].email || password.text.toString() != userList[i].password) {
                println("\t * - BUTTON ACTION HAS FAILED - \n\t   => \"Login\"")
                Toast.makeText(this, "Oops! Wrong email/password, try again!", Toast.LENGTH_LONG).show()
            }

            /* TODO - Add missing LOGIN button to activity_login.xml                            - DONE - */
            /* TODO - Input type should be: Password and Email types. (check XML)               - DONE - */
            /* TODO - .apply to send user information from input field BACK to MainActivity     - DONE - */

        }

        /* BUTTON: NEW USER */

        btnNewUser.setOnClickListener {
            println("\t * - BUTTON ACTION CALLED - \n\t   => \"New User\"")
            val intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)
        }

        /* BUTTON: BACK */

        btnBack.setOnClickListener {
            println("\t * - BUTTON ACTION CALLED -\n\t   => \"Back\"")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val clickableLoadUsers = findViewById<TextView>(R.id.clickable_load_users)
        clickableLoadUsers.setOnClickListener {
            println("\t * - BUTTON ACTION CALLED -\n\t   => \"Load Users\"")
            userList = viewModel.arrayList()
            for (users in userList) {
                user = User(users.firstName, users.lastName, users.email, users.password)
            }
            println("\t\t   ...DONE")
        }

        val clickablePrint = findViewById<TextView>(R.id.clickable_print)
        clickablePrint.setOnClickListener {
            println("\t * - BUTTON ACTION CALLED -\n\t   => \"Print\"")
            if (userList.isNotEmpty()) {
                println("\n\t * FOUND USER'S IN LIST *\n" +
                        "\t   --------------------")
                for (users in userList) {
                    println(
                        "\n\t\t * USER *\n" +
                                "\t\t   ----\n" +
                                "\t\tFirstname: ${users.firstName}\n" +
                                "\t\tLastname: ${users.lastName}\n" +
                                "\t\tE-mail: ${users.email}\n" +
                                "\t\tPassword: ${users.password}\n"
                    )
                }
                println("\n\t * \tEND OF LIST\t *\n\n")
            } else println("\t\t * LIST EMPTY *\n\n")
        }
    }
}

