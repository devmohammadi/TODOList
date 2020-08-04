package com.example.todolist.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.adapter.ChoreListAdapter
import com.example.todolist.datalayers.ChoreDatabaseHandler
import com.example.todolist.models.Chore
import kotlinx.android.synthetic.main.activity_chore_list.*

class ChoreListActivity : AppCompatActivity() {

    private var adapter: ChoreListAdapter? = null
    private var dbHandler: ChoreDatabaseHandler? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    var choreList: ArrayList<Chore>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chore_list)

        choreList = ArrayList()
        layoutManager = LinearLayoutManager(this)
        dbHandler = ChoreDatabaseHandler(this)
        choreList = dbHandler!!.readChore()
        adapter = ChoreListAdapter(this,choreList!!)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter
        adapter!!.notifyDataSetChanged()
    }
}