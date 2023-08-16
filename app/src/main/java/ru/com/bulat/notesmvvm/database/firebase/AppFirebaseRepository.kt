package ru.com.bulat.notesmvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import ru.com.bulat.notesmvvm.database.DatabaseRepository
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.EMAIL
import ru.com.bulat.notesmvvm.utilits.ID_FIREBASE
import ru.com.bulat.notesmvvm.utilits.NAME
import ru.com.bulat.notesmvvm.utilits.PASSWORD
import ru.com.bulat.notesmvvm.utilits.TEXT
import ru.com.bulat.notesmvvm.utilits.showToast

class AppFirebaseRepository : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()
    private val mDatabaseReference = FirebaseDatabase.getInstance().reference
        .child(mAuth.currentUser?.uid.toString())

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        val idNote = mDatabaseReference.push().key.toString()
        val mapNote = hashMapOf<String,Any>()
        mapNote[ID_FIREBASE] = idNote
        mapNote[NAME] = note.name
        mapNote[TEXT] = note.text

        mDatabaseReference.child(idNote)
            .updateChildren(mapNote)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { showToast(it.message.toString()) }

    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {

    }

    override fun connectToDatabase(onSuccess: () -> Unit, onFail: (String) -> Unit) {
        mAuth.signInWithEmailAndPassword(EMAIL, PASSWORD)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener {
                mAuth.createUserWithEmailAndPassword(EMAIL, PASSWORD)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener {
                        onFail(it.message.toString())
                    }
            }
    }

    override fun signOut() {
        mAuth.signOut()
    }
}