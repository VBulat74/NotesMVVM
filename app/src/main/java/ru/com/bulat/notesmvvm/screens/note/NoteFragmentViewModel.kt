package ru.com.bulat.notesmvvm.screens.note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.REPOSITORY

class NoteFragmentViewModel (application: Application) : AndroidViewModel(application) {
    fun delete (note: AppNote, onSuccess : () -> Unit) = viewModelScope.launch (Dispatchers.IO) {
        REPOSITORY.delete(note) {
            viewModelScope.launch (Dispatchers.Main) { onSuccess() }
        }
    }
}