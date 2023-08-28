package com.sumita.takenotes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Entity_NoteTable")
data class NoteTable(
    @PrimaryKey(autoGenerate = true)
    val id:Int=0,
    @ColumnInfo("Title")
    var title:String,
    @ColumnInfo("Description")
    var description:String)


