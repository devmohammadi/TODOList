package com.example.todolist.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.models.Chore

class ChoreListAdapter(private var mContext: Context, private var listChore: ArrayList<Chore>) :
    RecyclerView.Adapter<ChoreListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var choreName = itemView.findViewById<TextView>(R.id.choreName)
        var assignedBy = itemView.findViewById<TextView>(R.id.assignedBy)
        var assignedTo = itemView.findViewById<TextView>(R.id.assignedTo)
        var assignedTime = itemView.findViewById<TextView>(R.id.assignedTime)

        fun bindView(chore: Chore) {
            choreName.text = chore.choreName
            assignedBy.text = chore.assignedBy
            assignedTo.text = chore.assignedTo
            assignedTime.text = chore.showHumanDate(chore)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listChore.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listChore[position])
    }
}