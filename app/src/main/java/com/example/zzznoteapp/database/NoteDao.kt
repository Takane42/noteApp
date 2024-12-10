package com.example.zzznoteapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.zzznoteapp.model.NoteModel

@Dao
interface NoteDao {
    @Insert
    fun insertData(noteData: NoteModel)

    @Query("SELECT * FROM NoteModel ORDER BY id DESC")
    fun getAll(): LiveData<List<NoteModel>>

    @Update
    fun updateData(noteData: NoteModel)

    @Query("DELETE FROM NoteModel WHERE id = :id")
    fun deleteDataById(id: Int)

//    @Delete
//    fun deleteData(noteData: NoteModel)
}