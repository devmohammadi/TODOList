package com.example.todolist.models

class Chore() {
    var id: Int? = null
    var choreName: String? = null
    var assignedBy: String? = null
    var assignedTo: String? = null
    var timeAssigned: Long? = null

    constructor(id: Int,choreName: String, assignedBy: String,assignedTo: String,timeAssigned: Long) : this() {
        this.id = id
        this.choreName = choreName
        this.assignedBy = assignedBy
        this.assignedTo = assignedTo
        this.timeAssigned = timeAssigned
    }
}