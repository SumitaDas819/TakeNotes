package com.sumita.takenotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesViewModel(private val notesRepository: NotesRepository): ViewModel() {
    val getNotes:LiveData<List<NoteTable>> = notesRepository.allNotes
    fun insertNote(noteTable: NoteTable){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.insert(noteTable)  }
    }
    fun updateNote(noteTable: NoteTable){
        viewModelScope.launch(Dispatchers.IO) {
            notesRepository.update(noteTable)
        }
    }
    fun deleteNote(noteTable: NoteTable){
        viewModelScope.launch(Dispatchers.IO) { notesRepository.delete(noteTable) }
    }

}