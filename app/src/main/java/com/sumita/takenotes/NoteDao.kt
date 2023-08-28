package com.sumita.takenotes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface  NoteDao {
    @Insert
    suspend fun insert(noteTable: NoteTable)
    @Update
    suspend fun update(noteTable: NoteTable)
    @Delete
    suspend fun delete(noteTable: NoteTable)
    @Query("Select * from Entity_NoteTable order by Title ASC")
    fun getAllNotes(): LiveData<List<NoteTable>>
}
//The class marked with @Dao should either be an interface or an
//abstract class. At compile time, Room will generate an implementation
//of this class when it is referenced by a Database.