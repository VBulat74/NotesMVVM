package ru.com.bulat.notesmvvm.database.firebase

import androidx.lifecycle.LiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.REF_DB

class AllNotesLiveData : LiveData<List<AppNote>>() {
    private val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot.children.map {
                it.getValue(AppNote::class.java)?: AppNote()
            }
        }

        override fun onCancelled(error: DatabaseError) {

        }
    }

    override fun onActive() {
        REF_DB.addValueEventListener(listener)
        super.onActive()
    }

    override fun onInactive() {
        REF_DB.removeEventListener(listener)
        super.onInactive()
    }
}