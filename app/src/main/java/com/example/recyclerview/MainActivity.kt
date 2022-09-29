package com.example.recyclerview

import TaskAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView=findViewById(R.id.RecycleViewer)
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val data=fetchData()
        val adapter =TaskAdapter(data)
        recyclerView.adapter=adapter
    }

    private fun fetchData():ArrayList<Map<String,String>>
    {
        val data=ArrayList<Map<String,String>>()
        for(i in 1..100)
        {
            val map=mapOf(
                "header" to "task $i",
                "description" to "description ${i*1000}"
            )
            data.add(map)
        }
        return data
    }
}