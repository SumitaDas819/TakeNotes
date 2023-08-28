package com.sumita.takenotes

import androidx.lifecycle.LiveData

class NotesRepository (private val noteDao: NoteDao) {
    val allNotes:LiveData<List<NoteTable>> = noteDao.getAllNotes()
    suspend fun insert(noteTable: NoteTable){
        noteDao.insert(noteTable)
    }
    suspend fun update(noteTable: NoteTable){
        noteDao.update(noteTable)
    }
    suspend fun delete(noteTable: NoteTable){
        noteDao.delete(noteTable)
    }
}