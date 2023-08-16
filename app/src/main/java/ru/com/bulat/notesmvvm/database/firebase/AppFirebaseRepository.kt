package ru.com.bulat.notesmvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import ru.com.bulat.notesmvvm.database.DatabaseRepository
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.EMAIL
import ru.com.bulat.notesmvvm.utilits.PASSWORD

class AppFirebaseRepository : DatabaseRepository {

    private val mAuth = FirebaseAuth.getInstance()

    override val allNotes: LiveData<List<AppNote>> = AllNotesLiveData()

    override suspend fun insert(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(note: AppNote, onSuccess: () -> Unit) {
        TODO("Not yet implemented")
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