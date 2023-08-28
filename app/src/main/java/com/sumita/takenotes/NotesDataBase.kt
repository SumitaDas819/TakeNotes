package com.sumita.takenotes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(arrayOf(NoteTable::class), version = 1, exportSchema = false)
abstract class NotesDataBase :RoomDatabase() {
    abstract fun getNoteDao(): NoteDao

    companion object{
        @Volatile//to tell all the threds that the value has updated
        private var INSTANCE:NotesDataBase?=null
        fun getDataBase(context: Context):NotesDataBase{
            if (INSTANCE==null){
                synchronized(this){
                    INSTANCE= Room.
                    databaseBuilder(context.applicationContext,NotesDataBase::class.java,"NoteDataBase").build()
                }

            }
            return INSTANCE!!
        }
    }
}