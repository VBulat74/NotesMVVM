package ru.com.bulat.notesmvvm.database

import androidx.lifecycle.LiveData
import ru.com.bulat.notesmvvm.model.AppNote

interface DatabaseRepository {
    val allNotes : LiveData<List<AppNote>>
    suspend fun insert(note: AppNote, onSuccess : () -> Unit)
    suspend fun delete(note: AppNote, onSuccess : () -> Unit)

    fun connectToDatabase(onSuccess : () -> Unit, onFail : (String) -> Unit) {}

    fun signOut (){}
}