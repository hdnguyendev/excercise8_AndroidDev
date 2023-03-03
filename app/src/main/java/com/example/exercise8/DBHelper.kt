package com.example.exercise8

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DB_NAME, factory, DB_VERSION) {
    companion object {
        private const val DB_NAME = "courseDB"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "myCourses"
        private const val ID_COL = "id"
        private const val NAME_COL = "name"
        private const val DESCRIPTION_COL = "description"
        private const val DURATION_COL = "duration"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE $TABLE_NAME (" +
                " $ID_COL INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $NAME_COL TEXT," +
                " $DESCRIPTION_COL TEXT," +
                " $DURATION_COL TEXT" +
                ")"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addNewCourse(
        courseName: String?,
        courseDesc: String?,
        courseDuration: String?,
    ) {
        val db = this.writableDatabase

        val values = ContentValues()

        values.put(NAME_COL, courseName)
        values.put(DESCRIPTION_COL, courseDesc)
        values.put(DURATION_COL, courseDuration)

        db.insert(TABLE_NAME, null, values)
        db.close()

    }

    fun readCourses(): ArrayList<Course>? {
        val db = this.readableDatabase

        val cursorCourses: Cursor? = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val courseModalArrayList: ArrayList<Course> = ArrayList()

        if (cursorCourses?.moveToFirst()!!) {
            do {
                courseModalArrayList.add(Course(cursorCourses.getString(1),cursorCourses.getString(2), cursorCourses.getString(3)))

            } while (cursorCourses.moveToNext())
        }
        cursorCourses.close()
        return courseModalArrayList

    }
}