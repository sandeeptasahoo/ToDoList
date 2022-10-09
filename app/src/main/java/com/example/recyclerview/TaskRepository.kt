package com.example.recyclerview

import androidx.lifecycle.LiveData

class TaskRepository(private val taskdao: DaoTask) {

    val alltask: LiveData<List<Task>> = taskdao.getAllTask()

    suspend fun insert(task: Task)
    {
        taskdao.insert(task)
    }
    suspend fun delete(task: Task)
    {
        taskdao.delete(task)
    }
}