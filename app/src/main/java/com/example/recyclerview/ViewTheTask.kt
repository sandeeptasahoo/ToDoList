package com.example.recyclerview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclerview.R

class ViewTheTask : AppCompatActivity(){
    lateinit var StartTime:TextView
    lateinit var EndTime:TextView
    lateinit var Title:TextView
    lateinit var description:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.viewtask)
        StartTime=findViewById(R.id.startTime)
        EndTime=findViewById(R.id.endTime)
        Title=findViewById(R.id.title)
        description=findViewById(R.id.taskAssign)
        AssignAllData()
    }

    fun AssignAllData()
    {
        val intent=intent
        StartTime.text="Start Time: "+intent.getStringExtra("StartTime")
        EndTime.text="End Time: "+intent.getStringExtra("EndTime")
        Title.text=intent.getStringExtra("header")
        description.text=intent.getStringExtra("description")
    }

}