package ru.com.bulat.notesmvvm.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.com.bulat.notesmvvm.database.room.AppRoomDatabase
import ru.com.bulat.notesmvvm.database.room.AppRoomRepository
import ru.com.bulat.notesmvvm.utilits.REPOSITORY
import ru.com.bulat.notesmvvm.utilits.TYPE_ROOM

class StartFragmentViewModel (application: Application) : AndroidViewModel(application) {

    private val mContext = application

    fun initDataBase(type:String, onSuccess : () -> Unit) {
        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase
                    .getInstance(mContext)
                    .getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }
        }
    }
}