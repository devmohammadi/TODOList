package com.example.todolist.adapter

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.controller.MainActivity
import com.example.todolist.datalayers.ChoreDatabaseHandler
import com.example.todolist.models.Chore
import kotlinx.android.synthetic.main.popup.view.*

class ChoreListAdapter(private var mContext: Context, private var listChore: ArrayList<Chore>) :
    RecyclerView.Adapter<ChoreListAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View, var mContext: Context, var arrayList: ArrayList<Chore>) :
        RecyclerView.ViewHolder(itemView) {
        var choreName = itemView.findViewById<TextView>(R.id.choreName)
        var assignedBy = itemView.findViewById<TextView>(R.id.assignedBy)
        var assignedTo = itemView.findViewById<TextView>(R.id.assignedTo)
        var assignedTime = itemView.findViewById<TextView>(R.id.assignedTime)
        var deletItem = itemView.findViewById<Button>(R.id.delete)
        var editItem = itemView.findViewById<Button>(R.id.edit)

        fun bindView(chore: Chore) {
            choreName.text = chore.choreName
            assignedBy.text = chore.assignedBy
            assignedTo.text = chore.assignedTo
            assignedTime.text = chore.showHumanDate(chore)

            var mPosition = adapterPosition
            var chore = arrayList[mPosition]


            deletItem.setOnClickListener {
                deleteItem(chore.id!!.toInt())
                arrayList.removeAt(mPosition)
                notifyItemRemoved(mPosition)
            }

            editItem.setOnClickListener {
                editItem(chore)
            }

        }

        fun deleteItem(id: Int) {
            val db = ChoreDatabaseHandler(mContext)
            db.deleteChore(id)
        }


        var alertDialogBuilder: AlertDialog.Builder? = null
        var dialogAlert: AlertDialog? = null
        var dbHandler = ChoreDatabaseHandler(mContext)

        fun editItem(chore: Chore) {
            var view = LayoutInflater.from(mContext).inflate(R.layout.popup, null)
            var popChoreName = view.poptvChore
            var popAssignedBy = view.poptvAssignedBy
            var popAssignedTo = view.poptvAssignedTo
            var popSaveButton = view.popbtn_SaveChore

            popChoreName.setText(chore.choreName)
            popAssignedBy.setText(chore.assignedBy)
            popAssignedTo.setText(chore.assignedTo)

            alertDialogBuilder = AlertDialog.Builder(mContext).setView(view)
            dialogAlert = alertDialogBuilder!!.create()
            dialogAlert!!.show()

            popSaveButton.setOnClickListener {
                var name = popChoreName.text.toString().trim()
                var assignedBy = popAssignedBy.text.toString().trim()
                var assignedTo = popAssignedTo.text.toString().trim()

                if (
                    !TextUtils.isEmpty(name) &&
                    !TextUtils.isEmpty(assignedBy) &&
                    !TextUtils.isEmpty(assignedTo)
                ) {
                    chore.choreName = name
                    chore.assignedBy = assignedBy
                    chore.assignedTo = assignedTo

                    dbHandler!!.updateChore(chore)
                    dialogAlert!!.dismiss()
                    notifyItemChanged(adapterPosition, chore)

                } else {
                    Toast.makeText(mContext, "please enter a chore !!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.list_row, parent, false)
        return ViewHolder(view, mContext, listChore)
    }

    override fun getItemCount(): Int {
        return listChore.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listChore[position])
    }
}