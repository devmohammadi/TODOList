package com.example.todolist.datalayers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.todolist.models.*
import java.sql.Date
import java.text.DateFormat

class ChoreDatabaseHandler(mContext: Context) :
    SQLiteOpenHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CHORE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_CHORE_NAME + " TEXT," +
                KEY_CHORE_ASSIGNED_BY + " TEXT," +
                KEY_CHORE_ASSIGNED_TO + " TEXT," +
                KEY_CHORE_ASSIGNED_TIME + " LONG)"

        db?.execSQL(CREATE_CHORE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

        //crete new table
        onCreate(db)
    }

    //CRUD -> CREATE , READ , UPDATE , DELETE

    fun createChore(chore: Chore) {
        val db: SQLiteDatabase = writableDatabase

        val values: ContentValues = ContentValues()
        values.put(KEY_CHORE_NAME, chore.choreName)
        values.put(KEY_CHORE_ASSIGNED_BY, chore.assignedBy)
        values.put(KEY_CHORE_ASSIGNED_TO, chore.assignedTo)
        values.put(KEY_CHORE_ASSIGNED_TIME, System.currentTimeMillis())
        db.insert(TABLE_NAME, null, values)
        db.close()

        Log.d("DATA INSERT ", "SUCCESS")
    }

    fun getAchor(id: Int): Chore? {
        val db: SQLiteDatabase = writableDatabase
        val cursor = db.query(
            TABLE_NAME, arrayOf(
                KEY_ID, KEY_CHORE_NAME,
                KEY_CHORE_ASSIGNED_BY,
                KEY_CHORE_ASSIGNED_TO,
                KEY_CHORE_ASSIGNED_TIME
            ),
            "$KEY_ID=?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )

        if (cursor != null) {
            cursor.moveToFirst()
            val chore = Chore()
            chore.choreName = cursor.getString(cursor.getColumnIndex(KEY_CHORE_NAME))
            chore.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_BY))
            chore.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_TO))
            chore.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_TIME))

            val dateFormated: DateFormat = DateFormat.getDateInstance()
            val formatedDate = dateFormated.format(
                Date(cursor.getLong(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_TIME))).time
            )
            return chore
        }
        return null
    }

    fun updateChore(chore: Chore): Int {
        val db: SQLiteDatabase = writableDatabase
        val values: ContentValues = ContentValues()
        values.put(KEY_CHORE_NAME, chore.choreName)
        values.put(KEY_CHORE_ASSIGNED_BY, chore.assignedBy)
        values.put(KEY_CHORE_ASSIGNED_TO, chore.assignedTo)
        values.put(KEY_CHORE_ASSIGNED_TIME, System.currentTimeMillis())

        //update a row from database
        return db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(chore.id.toString()))
    }

    fun deleteChore(chore: Chore) {
        val db: SQLiteDatabase = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(chore.id.toString()))
        db.close()
    }

    fun deleteChore(id: Int) {
        val db: SQLiteDatabase = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(id.toString()))
        db.close()
    }


    fun getChoreCount(): Int {
        val db: SQLiteDatabase = readableDatabase
        val countQuery = "SELECT * FROM "+TABLE_NAME
        val cursor: Cursor = db.rawQuery(countQuery, null)
        return cursor.count
    }

    fun readChore(): ArrayList<Chore> {
        val db: SQLiteDatabase = readableDatabase
        val list: ArrayList<Chore> = ArrayList()
        val selectAll = "SELECT * FROM " + TABLE_NAME

        val cursor: Cursor = db.rawQuery(selectAll, null)
        if (cursor.moveToFirst()) {
            do {
                val chore = Chore()
                chore.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                chore.choreName = cursor.getString(cursor.getColumnIndex(KEY_CHORE_NAME))
                chore.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_BY))
                chore.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_TO))
                chore.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_CHORE_ASSIGNED_TIME))

                list.add(chore)
            } while (cursor.moveToNext())
        }
        return list
        Log.d("DATA READ ", "SUCCESS")
    }

}