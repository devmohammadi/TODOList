package com.example.todolist.controller

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.todolist.R
import com.example.todolist.datalayers.ChoreDatabaseHandler
import com.example.todolist.models.Chore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var dbHandler: ChoreDatabaseHandler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterChore = tvChore
        val enterAssignedBy = tvAssignedBy
        val enterAssignedTo = tvAssignedTo
        val saveChore = btn_SaveChore

        dbHandler = ChoreDatabaseHandler(this)
        checkDB()
        saveChore.setOnClickListener {
            if (
                !TextUtils.isEmpty(enterChore.text.toString()) &&
                !TextUtils.isEmpty(enterAssignedBy.text.toString()) &&
                !TextUtils.isEmpty(enterAssignedTo.text.toString())
            ) {
                // save data to database
                val chore = Chore()
                chore.choreName = enterChore.text.toString()
                chore.assignedBy = enterAssignedBy.text.toString()
                chore.assignedTo = enterAssignedTo.text.toString()
                saveChoreToDatabase(chore)
                Toast.makeText(this, "your chore has been saved successfully", Toast.LENGTH_SHORT)
                    .show()
                startActivity(Intent(this, ChoreListActivity::class.java))
            } else {
                Toast.makeText(this, "please enter a chore !!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveChoreToDatabase(chore: Chore) {
        dbHandler!!.createChore(chore)
    }

    fun checkDB() {
        if (dbHandler!!.getChoreCount() > 0) {
            startActivity(Intent(this, ChoreListActivity::class.java))
            finish()
        }
    }
}