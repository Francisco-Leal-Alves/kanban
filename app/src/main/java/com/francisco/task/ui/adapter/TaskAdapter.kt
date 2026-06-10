package com.francisco.task.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.francisco.task.databinding.ItemTaskBinding
import com.francisco.task.data.model.Task
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.francisco.task.R
import com.francisco.task.data.model.Status

class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>,
    private val taskSelected: (Task, Int) -> Unit
): RecyclerView.Adapter<TaskAdapter.MyViewHolder> () {

    companion object{
        val SELECT_BACK: Int = 1
        val SELECT_REMOVER: Int = 2
        val SELECT_EDIT: Int = 3
        val SELECT_DETAILS: Int = 4
        val SELECT_NEXT: Int = 5
    }

    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): MyViewHolder {
        val view = ItemTaskBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val task = taskList[position]
        holder.binding.textDescription.text = task.description

        setIndicators(task, holder)
    }

    private fun setIndicators(task: Task, holder: MyViewHolder){
        when (task.status){
            Status.TODO -> {
                holder.binding.buttonBack.isVisible = false
                holder.binding.buttonForward.setOnClickListener { taskSelected(task, SELECT_NEXT)}
            }
            Status.DOING -> {
                //configurar a cor diferente para as setas
                holder.binding.buttonBack.setColorFilter( ContextCompat.getColor(context, R.color.color_status_todo))
                holder.binding.buttonForward.setColorFilter( ContextCompat.getColor(context, R.color.color_status_done))
                holder.binding.buttonForward.setOnClickListener { taskSelected(task, SELECT_NEXT)}
                holder.binding.buttonBack.setOnClickListener { taskSelected(task, SELECT_BACK)}
            }
            Status.DONE -> {
                holder.binding.buttonForward.isVisible = false
                holder.binding.buttonBack.setOnClickListener { taskSelected(task, SELECT_BACK)}
            }
        }

        holder.binding.buttonForward.setOnClickListener { taskSelected(task, SELECT_REMOVER)}
        holder.binding.buttonBack.setOnClickListener { taskSelected(task, SELECT_EDIT)}
        holder.binding.buttonForward.setOnClickListener { taskSelected(task, SELECT_DETAILS)}

    }
    override fun getItemCount() = taskList.size

    inner class MyViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root){
        // TODO: alguma coisa
    }

}