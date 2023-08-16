package ru.com.bulat.notesmvvm.screens.add_news_note

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.com.bulat.notesmvvm.model.AppNote
import ru.com.bulat.notesmvvm.utilits.REPOSITORY

class AddNewNoteFragmentViewModel (application: Application) : AndroidViewModel(application) {
    fun insert(note : AppNote, onSuccess : () -> Unit) = viewModelScope.launch (Dispatchers.IO) {
        REPOSITORY.insert(note) {
            viewModelScope.launch(Dispatchers.Main) { onSuccess() }
        }
    }
}