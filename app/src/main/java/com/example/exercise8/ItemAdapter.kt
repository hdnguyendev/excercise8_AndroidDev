package com.example.exercise8

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val context: Context,
    private val courseArrayList: ArrayList<Course>,
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvCourseName: TextView? = view.findViewById(R.id.tvCourseName)
        val tvCourseDescription: TextView? = view.findViewById(R.id.tvCourseDesc)
        val tvCourseDuration: TextView? = view.findViewById(R.id.tvCourseDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout =
            LayoutInflater.from(parent.context).inflate(R.layout.course_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val modal: Course = courseArrayList.get(position)
        holder.tvCourseName!!.text = modal.courseName
        holder.tvCourseDescription!!.text = modal.courseDesc
        holder.tvCourseDuration!!.text = modal.courseDuration

        holder.itemView.setOnClickListener{
            Log.i(TAG, "onBindViewHolder: clicked")
        }
    }

    override fun getItemCount(): Int {
        return courseArrayList.size
    }
}