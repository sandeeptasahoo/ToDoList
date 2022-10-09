package com.example.recyclerview

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
class Task(@ColumnInfo(name="subject")val subject:String,@ColumnInfo(name="desrciption")val description:String,
           @ColumnInfo(name="StartTime")val StartTime:String,@ColumnInfo(name="EndTime") val EndTime:String
           ) {
    @PrimaryKey(autoGenerate = true)  var id:Int=0

}