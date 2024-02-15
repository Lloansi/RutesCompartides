package com.example.rutescompartidesapp.data.domain

import android.provider.ContactsContract.CommonDataKinds.Phone

data class User(val userId: Int, val name: String, val email: String, val phone: Int, val password: String)
