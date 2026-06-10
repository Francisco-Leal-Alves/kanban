package com.francisco.task.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.francisco.task.R
import com.francisco.task.data.model.Status
import com.francisco.task.data.model.Task
import com.francisco.task.databinding.FragmentDoneBinding
import com.francisco.task.ui.adapter.TaskAdapter
import com.google.firebase.auth.FirebaseAuth


class DoneFragment : Fragment() {
    private lateinit var taskAdapter: TaskAdapter
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerViewTask(getTask())
    }

    private fun initListeners() {
        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate((R.id.action_homeFragment_to_formTaskFragment))
        }
    }

    private fun getTask() = listOf(
        Task(id = "0", description = "Criar nova tela do app", Status.TODO),
        Task(id = "1", description = "Validar informações na tela de login", Status.TODO),
        Task(id = "2", description = "Adicionar nova funcionalidade no app", Status.TODO),
        Task(id = "3", description = "Salvar token localmente", Status.TODO),
        Task(id = "2", description = "Criar funcionalidade de logout no app", Status.TODO),
    )

    private fun initRecyclerViewTask(taskList: List<Task>){

        taskAdapter = TaskAdapter(requireContext(),taskList)
        binding.recyclerViewTask.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTask.setHasFixedSize(true)

        binding.recyclerViewTask.adapter = taskAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}