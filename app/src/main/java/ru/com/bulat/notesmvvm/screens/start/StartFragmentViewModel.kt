package ru.com.bulat.notesmvvm.screens.start

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.com.bulat.notesmvvm.database.firebase.AppFirebaseRepository
import ru.com.bulat.notesmvvm.database.room.AppRoomDatabase
import ru.com.bulat.notesmvvm.database.room.AppRoomRepository
import ru.com.bulat.notesmvvm.utilits.EMAIL
import ru.com.bulat.notesmvvm.utilits.PASSWORD
import ru.com.bulat.notesmvvm.utilits.REPOSITORY
import ru.com.bulat.notesmvvm.utilits.TYPE_FIREBASE
import ru.com.bulat.notesmvvm.utilits.TYPE_ROOM
import ru.com.bulat.notesmvvm.utilits.showToast

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
            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase({
                    onSuccess()
                                             },{
                    showToast(it)
                                             })
            }
        }
    }
}