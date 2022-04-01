package com.dvczr.lab_2

import androidx.lifecycle.ViewModel

class UserViewModel: ViewModel() {

    var userList: ArrayList<User> = ArrayList()

    fun arrayList(): ArrayList<User> {
        userList.add(User(firstName = "Alice", lastName = "Knight", email = "a.k@app.com", password = "AK47"))
        userList.add(User(firstName = "Benny", lastName = "Bananas", email = "monkey.eat@bananas.com", password = "qwerty"))
        return userList
    }


    /* TODO -
    *       Implement USER                              - DONE -
    *       Extend to ViewModel (SOLO GROUPS ONLY)      - DONE -
    * */
}