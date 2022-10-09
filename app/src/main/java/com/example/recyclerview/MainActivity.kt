package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), onClickofTask
{
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: TaskViewModel


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Toast.makeText(this,"application started",Toast.LENGTH_LONG).show()
        recyclerView=findViewById(R.id.RecycleViewer)
        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        //addInputData()
        val adapter = TaskAdapter(this)
        recyclerView.adapter=adapter

            viewModel=ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
                .get(TaskViewModel::class.java)
          viewModel.allTask.observe(this, Observer{
                    list->list?.let{
                adapter.UpdateList(it)
                        Toast.makeText(this,"Task is added",Toast.LENGTH_LONG).show()
            }

           })




    }

    fun fetchData():List<Task>
    {
        var data= mutableListOf<Task>()

        for (i in 1..100) {
            val task = Task(
                "task  $i",
                "description ${i * 1000}",
                "${i % 12} AM",
                "${(i + 2) % 12} AM",

            )
            data.add(task)

        }
        return data.toList()

    }

    override fun showTaskDetails(data: Task) {
        //Log.d("clickCkeck","${data["header"]} is clicked")
       /* val intent= Intent(this, ViewTheTask::class.java)

        for(i in data)
        {
            intent.putExtra(i.key,i.value)
        }
        startActivity(intent)

        */
       // viewModel.deleteTask(data)
    }

    fun dataParternChange(data:List<Task>):ArrayList<Map<String,String>>
    {
        val newdata=ArrayList<Map<String,String>>()
        for(t in data)
        {
            val m= mapOf<String,String>(
                "header" to t.subject,
                "description" to t.description,
                "StartTime" to t.StartTime,
                "EndTime" to t.EndTime
            )
            newdata.add(m)
        }
        return newdata
    }

    fun addInputData()
    {
        val data=intent.extras

        if(data!=null)
        {
            val task=Task(intent.getStringExtra("subject")?:"No Subject",
            intent.getStringExtra("description")?:"No Description",
            intent.getStringExtra("StartTime")?:"None",
            intent.getStringExtra("EndTime")?:"None")
            viewModel.insertTask(task)
        }
    }

    fun AddTask(view: View) {
        val intent= Intent(this, TaskInput::class.java)

        startActivity(intent)
    }

}