package com.francisco.task.ui.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.francisco.task.databinding.ItemTaskBinding
import com.francisco.task.data.model.Task
import android.view.LayoutInflater
class TaskAdapter(
    private val taskList: List<Task>
): RecyclerView.Adapter<TaskAdapter.MyViewHolder> () {
    override fun onCreateViewHolder(
        p0: ViewGroup,
        p1: Int
    ): MyViewHolder {
        val view = ItemTaskBinding.inflate(LayoutInflater.from(p0.context), p0, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        p0: MyViewHolder,
        p1: Int
    ) {
        val task = taskList[p1]
        p0.binding.textDescription.text = task.description
    }

    override fun getItemCount() = taskList.size

    inner class MyViewHolder(val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root){
        // TODO: alguma coisa
    }

}