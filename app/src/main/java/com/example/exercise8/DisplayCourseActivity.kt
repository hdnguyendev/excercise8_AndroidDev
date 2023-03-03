package com.example.exercise8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DisplayCourseActivity : AppCompatActivity() {
    lateinit var db: DBHelper
    lateinit var adapter: ItemAdapter
    lateinit var rvCourse: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_course)
        db = DBHelper(this, null)

        var courseArrayList: ArrayList<Course>
        courseArrayList = db.readCourses()!!

        rvCourse = findViewById<RecyclerView>(R.id.rvCourseList)
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvCourse.layoutManager = linearLayoutManager
        rvCourse.adapter = ItemAdapter(this, courseArrayList)

    }
}
