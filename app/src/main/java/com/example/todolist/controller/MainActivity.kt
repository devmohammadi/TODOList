package com.example.todolist.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.todolist.R
import com.example.todolist.datalayers.ChoreDatabaseHandler
import com.example.todolist.models.Chore

class MainActivity : AppCompatActivity() {
    var dbHandler: ChoreDatabaseHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHandler = ChoreDatabaseHandler(this)

        var newChore = Chore()
        newChore.choreName = "Publish Site"
        newChore.assignedBy = "zahra"
        newChore.assignedTo = "fatemeh"

        dbHandler?.createChore(newChore)
    }
}