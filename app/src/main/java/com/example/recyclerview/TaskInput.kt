package com.example.recyclerview

import android.annotation.SuppressLint
import android.app.ActivityManager.TaskDescription
import android.content.ClipData.Item
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class TaskInput:AppCompatActivity() {
    lateinit var taskType:Spinner
    lateinit var TaskTypeValue:String
    lateinit var displayDate:TextView
    lateinit var PickDate:DatePicker
    lateinit var dateSelectButton:Button
    lateinit var displayStartTime:TextView
    lateinit var pickStartTime:TimePicker
    lateinit var StartTimeSelectButton:Button
    lateinit var displayEndTime:TextView
    lateinit var pickEndTime:TimePicker
    lateinit var EndTimeSelectButton:Button
    lateinit var WeekDaysSelection:TextView
    lateinit var TaskSubject:EditText
    lateinit var TaskDescription:EditText


    var dateSelected:Boolean=true
    var StartTimeSelected:Boolean=true
    var EndTimeSelected:Boolean=true

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.input_task)
        referenceAllUIobject()
        DropDownMenuForTaskType()



    }

    fun MultipleSelectorForWeek(view: View)
    {
        //Alert box design
        var builder=AlertDialog.Builder(this)
        builder.setTitle("Select Days To Remind")
        builder.setCancelable(false)
        val days= arrayOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
        val SelectedDays= BooleanArray(days.size)
        val daysSelected= mutableListOf<String>()
        builder.setMultiChoiceItems(days,SelectedDays){
            dialog,which,isChecked->
            SelectedDays[which]=isChecked
        }
        builder.setPositiveButton("SUBMIT"){
            dialog,which->
            for(i in SelectedDays.indices)
            {
                if(SelectedDays[i])
                {
                    val day=days[i].substring(0,3)
                    daysSelected.add(day)
                }
            }
            if(daysSelected.size==0)
            {
                Toast.makeText(this, "No days has been selected", Toast.LENGTH_SHORT).show()
                WeekDaysSelection.text=""
            }
            else if(daysSelected.size==7)
            {
                WeekDaysSelection.text="Daily"
            }

            else
            {
                var dialog:String=""
                for(i in daysSelected)
                {
                    dialog+=i+", "
                }
                WeekDaysSelection.text=dialog
            }

        }

        builder.setNegativeButton("CANCEL")
        {
            dialog,which->
            WeekDaysSelection.text=""
        }
        builder.create().show()


    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun StartTimeSelecter(view: View)
    {

        StartTimeSelected=!StartTimeSelected
        if(StartTimeSelected)
        {
            StartTimeSelectButton.text="Select Time"
            pickStartTime.visibility=View.GONE
            val Starttime="${pickStartTime.hour} : ${pickStartTime.minute}  "
            displayStartTime.text=Starttime
        }
        else
        {
            StartTimeSelectButton.text="Submit"
            pickStartTime.visibility=View.VISIBLE
            displayStartTime.text=""
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun EndTimeSelecter(view: View)
    {

        EndTimeSelected=!EndTimeSelected
        if(EndTimeSelected)
        {
            EndTimeSelectButton.text="Select Time"
            pickEndTime.visibility=View.GONE
            val Endtime="${pickEndTime.hour} : ${pickEndTime.minute}  "
            displayEndTime.text=Endtime
        }
        else
        {
            EndTimeSelectButton.text="Submit"
            pickEndTime.visibility=View.VISIBLE
            displayEndTime.text=""
        }
    }


    fun selectDate(view: View)
    {

        dateSelected=!dateSelected
        if(dateSelected)
        {
            dateSelectButton.text="Select Date"
            PickDate.visibility=View.GONE
            var date:String="${PickDate.dayOfMonth} / ${PickDate.month+1} / ${PickDate.year}"
            displayDate.text=date
        }
        else
        {
            dateSelectButton.text="Submit"
            PickDate.visibility=View.VISIBLE
            displayDate.text=""
        }

    }

    fun CheckBlankOrNot(text:String,VariableName:String):Boolean
    {
        if(text=="")
        {
            Toast.makeText(this,"Assign Task $VariableName",Toast.LENGTH_LONG).show()
            return false;
        }
        return true
    }

    fun SubmitTask(view:View)
    {
        val task=Task(TaskSubject.text.toString(),
            TaskDescription.text.toString(),
            displayStartTime.text.toString(),
            displayEndTime.text.toString())
        if(CheckBlankOrNot(task.subject,"Subject") &&
            CheckBlankOrNot(task.description,"Description") &&
            CheckBlankOrNot(task.StartTime,"Start Time") &&
            CheckBlankOrNot(task.EndTime,"End Time"))
        {
            val intent= Intent(this, MainActivity::class.java)
            intent.putExtra("subject",task.subject)
            intent.putExtra("description",task.description)
            intent.putExtra("StartTime",task.StartTime)
            intent.putExtra("EndTime",task.EndTime)
            startActivity(intent)
        }

    }


    fun referenceAllUIobject()
    {
        TaskSubject=findViewById(R.id.TaskSubject)
        TaskDescription=findViewById(R.id.TaskDescription)
        taskType=findViewById(R.id.TaskType)
        displayDate=findViewById(R.id.DateDisplay)
        PickDate=findViewById(R.id.DateSelector)
        dateSelectButton=findViewById(R.id.SelectDateButton)
        displayStartTime=findViewById(R.id.TaskStartTime)
        displayEndTime=findViewById(R.id.TaskEndTime)
        pickStartTime=findViewById(R.id.TaskStartTimePicker)
        pickEndTime=findViewById(R.id.TaskEndTimePicker)
        StartTimeSelectButton=findViewById(R.id.TaskStartButton)
        EndTimeSelectButton=findViewById(R.id.TaskEndButton)
        WeekDaysSelection=findViewById(R.id.DaySelector)
    }

    fun DropDownMenuForTaskType()
    {
        val TypeOfTask= arrayOf("Once","Daily","Weekly","Monthly","Yearly")
        taskType.adapter=ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,TypeOfTask)


        taskType.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                TaskTypeValue=TypeOfTask.get(p2)
                TypeSelection()
                //Toast.makeText(applicationContext,"Task type is $TaskTypeValue",Toast.LENGTH_LONG).show()

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(applicationContext,"Select the type of task",Toast.LENGTH_LONG).show()
            }

        }
    }

    fun TypeSelection()
    {
        Log.d("Task input","Task Type is $TaskTypeValue")
        if(TaskTypeValue=="Once")
        {
            displayDate.visibility=View.VISIBLE
            dateSelectButton.visibility=View.VISIBLE

        }
        else
        {
            displayDate.visibility=View.GONE
            dateSelectButton.visibility=View.GONE
        }
        if(TaskTypeValue=="Weekly")
        {
            WeekDaysSelection.visibility=View.VISIBLE
        }
        else
        {
            WeekDaysSelection.visibility=View.GONE
        }
    }

}