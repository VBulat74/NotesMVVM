package ru.com.bulat.notesmvvm.utilits

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import ru.com.bulat.notesmvvm.MainActivity
import ru.com.bulat.notesmvvm.database.DatabaseRepository

lateinit var APP_ACTIVITY : MainActivity
lateinit var REPOSITORY : DatabaseRepository
lateinit var EMAIL : String
lateinit var PASSWORD : String
lateinit var FIREBASE_AUTH : FirebaseAuth
lateinit var CURRENT_USER_ID : String
lateinit var REF_DB : DatabaseReference

const val TYPE_ROOM = "type_room"
const val TYPE_FIREBASE = "type_firebase"

const val ID_FIREBASE = "idFirebase"
const val NAME = "name"
const val TEXT = "text"

