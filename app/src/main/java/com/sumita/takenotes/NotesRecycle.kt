package com.sumita.takenotes

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.add_update_lay_item.*
import kotlinx.android.synthetic.main.notes_item.view.*
import kotlinx.coroutines.NonDisposableHandle.parent

class NotesRecycle(private val listener: handledelete,private val viewModel: NotesViewModel) : RecyclerView.Adapter<NotesRecycle.NotesViewHolder>() {
    private  var itemClickListener: NoteItemClickListener?=null
    var allNotes = ArrayList<NoteTable>()

    inner class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = NotesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false))

        viewHolder.itemView.ivdelete.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        val currentNotes = allNotes[position]
        holder.itemView.tvtitle.text = currentNotes.title
        holder.itemView.tvdescription.text = currentNotes.description

        holder.itemView.setOnClickListener {
            val dialogUpdate = Dialog(holder.itemView.context)
            dialogUpdate.setContentView(R.layout.add_update_lay_item)
            dialogUpdate.btnADD.text = "UPDATE"
            dialogUpdate.tvText.setText("UPDATE NOTES")
            dialogUpdate.etTitle.setText(allNotes[position].title)
            dialogUpdate.etDescription.setText(allNotes[position].description)

            dialogUpdate.btnADD.setOnClickListener {
                val notes = NoteTable(0, dialogUpdate.etTitle.text.toString(), dialogUpdate.etDescription.text.toString())
                val updatedNote = allNotes[position].copy(title = dialogUpdate.etTitle.text.toString(), description = dialogUpdate.etDescription.text.toString())
                allNotes[position] = updatedNote
                itemClickListener?.onNoteUpdate(notes)
                dialogUpdate.dismiss()
                viewModel.updateNote(updatedNote) // Call the update function on the ViewModel
                notifyItemChanged(position)
//                the copy() function to create a copy of the existing note at the specified position (position) in the allNotes list.
//                The copy() function allows us to create a new instance with the same values as the existing note but with updated values
//                for the title and description fields.
            }

            dialogUpdate.show()
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }
    fun updatelist(newlist:List<NoteTable>){
        allNotes.clear()
        allNotes.addAll(newlist)
        notifyDataSetChanged()
    }
    fun setNoteItemClickListener(listener: NoteItemClickListener) {
        itemClickListener = listener
    }
}
interface handledelete{
    fun onItemClicked(noteTable: NoteTable)
}
interface NoteItemClickListener {
    fun onNoteUpdate(note: NoteTable)
}