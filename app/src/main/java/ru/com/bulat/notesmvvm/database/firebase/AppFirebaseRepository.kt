package ru.com.bulat.notesmvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.com.bulat.notesmvvm.database.DatabaseRepository
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.CURRENT_USER_ID
import ru.com.bulat.notesmvvm.utilits.EMAIL
import ru.com.bulat.notesmvvm.utilits.FIREBASE_AUTH
import ru.com.bulat.notesmvvm.utilits.ID_FIREBASE
import ru.com.bulat.notesmvvm.utilits.NAME
import ru.com.bulat.notesmvvm.utilits.PASSWORD
import ru.com.bulat.notesmvvm.utilits.REF_DB
import ru.com.bulat.notesmvvm.utilits.TEXT
import ru.com.bulat.notesmvvm.utilits.showToast

class AppFirebaseRepository : DatabaseRepository {
    init {
        FIREBASE_AUTH = FirebaseAuth.getInstance()

    }

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = REF_DB.push().key.toString()
        val mapNote = hashMapOf<String,Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        REF_DB.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }

    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        REF_DB.child(note.idFirebase).removeValue()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }
    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        FIREBASE_AUTH.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                FIREBASE_AUTH.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFail(it.message.toString())
                    }
            }
        CURRENT_USER_ID = FIREBASE_AUTH.currentUser?.uid.toString()
        REF_DB = FirebaseDatabase.getInstance().reference
            .child(CURRENT_USER_ID)
    }

    override fun signOut() {
        FIREBASE_AUTH.signOut()
    }
}