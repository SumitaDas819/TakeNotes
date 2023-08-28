package com.sumita.takenotes

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.room.InvalidationTracker
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_update_lay_item.*

class MainActivity : AppCompatActivity(), handledelete{
    lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview.layoutManager=LinearLayoutManager(this)
        val dao=NotesDataBase.getDataBase(applicationContext).getNoteDao()
        val repository=NotesRepository(dao)
        viewModel=ViewModelProvider(this,NoteViewModeFactory(repository)).get(NotesViewModel::class.java)
        val adapter=NotesRecycle(this,viewModel)
        recyclerview.adapter=adapter

        viewModel.getNotes.observe(this, Observer { list->
            list?.let { adapter.updatelist(it) }


        })
        btnadd.setOnClickListener{
            val diolog=Dialog(this)
            diolog.setContentView(R.layout.add_update_lay_item)
            diolog.btnADD.setOnClickListener {
                val title=diolog.etTitle.text.toString()
                val description=diolog.etDescription.text.toString()
                if(title.isNotEmpty() && description.isNotEmpty()){
                    viewModel.insertNote(NoteTable(0,title,description))
                }
                else
                    Toast.makeText(this,"Title and description must be field",Toast.LENGTH_SHORT).show()
                diolog.dismiss()
            }
            diolog.show()
        }
    }

    override fun onItemClicked(noteTable: NoteTable) {
        viewModel.deleteNote(noteTable)

    }


}