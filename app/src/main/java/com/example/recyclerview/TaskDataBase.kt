package com.example.recyclerview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
abstract class TaskDataBase: RoomDatabase() {
    abstract fun getDaoTask():DaoTask

    companion object{
//        val migration_1_2=object : Migration(1,2)
//        {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE task_table DROP COLUMN StartTimeInInt")
//            }
//        }

        @Volatile
        private var instace:TaskDataBase?=null

        fun getdatabase(context: Context):TaskDataBase
        {
            return instace?: synchronized(this)
            {

                val inst= Room.databaseBuilder(
                    context.applicationContext,TaskDataBase::class.java,"task_dataBase")
                .build()
                instace =inst

                inst
            }
        }
    }
}