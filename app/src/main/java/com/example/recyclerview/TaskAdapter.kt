package com.example.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val onClick: onClickofTask): RecyclerView.Adapter<TaskViewHolder>() {

    var data=listOf<Task>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.task,parent,false)
        val viewHolder= TaskViewHolder(view)
        view.setOnClickListener{
           //Log.d("click check","${data[viewHolder.absoluteAdapterPosition]["header"]} is clicked")
            onClick.showTaskDetails(data[viewHolder.absoluteAdapterPosition])

        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentData=data[position]
        holder.header.text=currentData.subject
        holder.description.text=currentData.description
        holder.startTime.text=currentData.StartTime
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun UpdateList(newdata:List<Task>)
    {
        data=newdata
        notifyDataSetChanged()
    }
}

interface onClickofTask
{
    fun showTaskDetails(data:Task)


}

class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    val task:ConstraintLayout=itemView.findViewById(R.id.TaskContainer)
    val header:TextView=itemView.findViewById(R.id.Heading)
    val description:TextView=itemView.findViewById(R.id.Description)
    val startTime:TextView=itemView.findViewById(R.id.starttime)

}

