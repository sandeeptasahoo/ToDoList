package com.example.recyclerview


import androidx.lifecycle.LiveData
import androidx.room.*
@Dao
interface DaoTask {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(task: Task)


    @Delete
    fun delete(task: Task)

    @Query("DELETE from task_table ")
    fun deleteAll()

    @Query("Select * from task_table")
     fun getAllTask(): LiveData<List<Task>>
}