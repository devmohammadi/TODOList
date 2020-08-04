package com.example.todolist.models

import java.text.SimpleDateFormat
import java.util.*

class Chore() {
    var id: Int? = null
    var choreName: String? = null
    var assignedBy: String? = null
    var assignedTo: String? = null
    var timeAssigned: Long? = null

    fun showHumanDate(chore: Chore): String {
        val simple = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        var result = Date(chore.timeAssigned!!)
        return simple.format(result)
    }

    constructor(
        id: Int,
        choreName: String,
        assignedBy: String,
        assignedTo: String,
        timeAssigned: Long
    ) : this() {
        this.id = id
        this.choreName = choreName
        this.assignedBy = assignedBy
        this.assignedTo = assignedTo
        this.timeAssigned = timeAssigned
    }
}