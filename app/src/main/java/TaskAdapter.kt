import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R

class TaskAdapter(private val data:ArrayList<Map<String,String>>): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.task,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentData=data[position]
        holder.header.text=currentData["header"]
        holder.description.text=currentData["description"]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
{
    val task:ConstraintLayout=itemView.findViewById(R.id.TaskContainer)
    val header:TextView=itemView.findViewById(R.id.Heading)
    val description:TextView=itemView.findViewById(R.id.Description)

}