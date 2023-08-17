package ru.com.bulat.notesmvvm.screens.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.com.bulat.notesmvvm.utilits.REPOSITORY

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val allNotes = REPOSITORY.allNotes

    fun signOut() {
        REPOSITORY.signOut()
    }
}