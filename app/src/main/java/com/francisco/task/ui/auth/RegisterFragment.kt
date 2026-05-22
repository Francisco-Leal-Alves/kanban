package com.francisco.task.ui.auth

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.francisco.task.R
import com.francisco.task.databinding.FragmentRegisterBinding
import com.francisco.task.util.initToolbar
import com.francisco.task.util.showBottomSheet
import com.google.firebase.auth.FirebaseAuth
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListener()
    }

    private fun initListener(){
        binding.buttonRegister.setOnClickListener {
            validateData()
        }
    }

    private fun validateData() {
        val email = binding.textEmail.text.toString().trim()
        val senha = binding.textSenha.text.toString().trim()

        if (email.isNotBlank()) {
            if (senha.isNotBlank()) {
                // Comentário temporário somente para testar a validação dos dados
                findNavController().navigate(R.id.action_global_homeFragment)

            } else {
                showBottomSheet(message = R.string.password_empty_register_fragment)
            }
        } else {
            showBottomSheet(message = R.string.email_empty_register_fragment)
        }
    }

    private fun registerUser(email: String, password: String){

        try {
            val auth = FirebaseAuth.getInstance()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSucessful){
                        findNavController().navigate(R.id.action_global_homeFragment)
                    } else {
                        Toast.makeText(requireContext, task.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
        } catch (e: Exception){
            Toast.makeText(requireContext(),e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}