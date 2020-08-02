package com.example.todolist.datalayers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.todolist.models.*

class ChoreDatabaseHandler(mContext: Context):
    SQLiteOpenHelper(mContext, DATABASE_NAME,null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        var CREATE_CHORE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_CHORE_NAME + " TEXT," +
                KEY_ASSIGNED_BY + " TEXT," +
                KEY_ASSIGNED_TO + " TEXT," +
                KEY_CHORE_ASSIGNED_TIME + " LONG)"

        db?.execSQL(CREATE_CHORE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}