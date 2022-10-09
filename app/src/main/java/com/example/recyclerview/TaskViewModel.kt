package com.example.recyclerview

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {
    val allTask:LiveData<List<Task>>
    val repository:TaskRepository

    init{
        val dao=TaskDataBase.getdatabase(application).getDaoTask()
        repository=TaskRepository(dao)
        allTask=repository.alltask
       // Toast.makeText(application.applicationContext,"New viewModel is Created",Toast.LENGTH_LONG).show()
    }

    fun deleteTask(task:Task)=viewModelScope.launch(Dispatchers.IO)
    {
        repository.delete(task)
    }

    fun insertTask(task:Task)=viewModelScope.launch(Dispatchers.IO)
    {
        repository.insert((task))
    }

}