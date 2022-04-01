package com.dvczr.lab_2

import android.os.Parcel
import android.os.Parcelable

class User : Parcelable {
    var firstName: String?
    var lastName: String?
    var email: String?
    var password: String?

    constructor(firstName: String?, lastName: String?, email: String?, password: String?) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.password = password
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(firstName)
        dest.writeString(lastName)
        dest.writeString(email)
        dest.writeString(password)
    }

    constructor(parcel: Parcel) {
        firstName = parcel.readString()
        lastName = parcel.readString()
        email = parcel.readString()
        password = parcel.readString()
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User?> =
            object : Parcelable.Creator<User?> {
                override fun createFromParcel(parcel: Parcel): User {
                    return User(parcel)
                }

                override fun newArray(size: Int): Array<User?> {
                    return arrayOfNulls(0)
                }
            }
    }
}