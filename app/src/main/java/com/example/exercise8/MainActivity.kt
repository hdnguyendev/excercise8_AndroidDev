package com.example.exercise8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var edtCourseName: EditText
    lateinit var edtCourseDescription: EditText
    lateinit var edtCourseDuration: EditText
    lateinit var btnAddCourse: Button
    lateinit var btnReadCourses: Button
    lateinit var db: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edtCourseName = findViewById(R.id.edtCourseName)
        edtCourseDescription = findViewById(R.id.edtCourseDesc)
        edtCourseDuration = findViewById(R.id.edtCourseDuration)
        btnAddCourse = findViewById(R.id.btnAddCourse)
        btnReadCourses = findViewById(R.id.btnReadCourse)
        db = DBHelper(this, null)
        btnAddCourse!!.setOnClickListener {
            addNewCourse()

        }
        btnReadCourses!!.setOnClickListener {
            val i = Intent(this@MainActivity, DisplayCourseActivity::class.java)

            startActivity(i)
        }
    }

    private fun addNewCourse() {
        val courseName: String = edtCourseName.text.toString()
        val courseDesc: String = edtCourseDescription.text.toString()
        val courseDuration: String = edtCourseDuration.text.toString()

        if (courseName.isEmpty() && courseDesc.isEmpty() && courseDuration.isEmpty()){
            Toast.makeText(this, "Please enter all the data ...", Toast.LENGTH_SHORT).show()
        } else {
            db.addNewCourse(courseName, courseDesc,courseDuration)
            Toast.makeText(this, "Course has been added.", Toast.LENGTH_SHORT).show()
            edtCourseName.setText("")
            edtCourseDescription.setText("")
            edtCourseDuration.setText("")
        }

    }
}