package ru.com.bulat.notesmvvm.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.com.bulat.notesmvvm.model.AppNote

@Dao
interface AppRoomDao {
    @Query("SELECT * FROM notes_table")
    fun getAllNotes() : LiveData<List<AppNote>>

    @Insert
    suspend fun insert (note: AppNote)

    @Delete
    suspend fun delete(note: AppNote)
}