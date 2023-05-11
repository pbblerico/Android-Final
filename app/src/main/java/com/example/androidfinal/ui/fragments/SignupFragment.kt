package com.example.androidfinal.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.androidfinal.R
import com.example.androidfinal.utils.Result
import com.example.androidfinal.databinding.FragmentSignupBinding
import com.example.androidfinal.viewModel.SignUpViewModel

class SignupFragment : Fragment(R.layout.fragment_signup) {
    private lateinit var binding: FragmentSignupBinding
    private lateinit var viewModel: SignUpViewModel


    private var username: String = ""
    private var email: String = ""
    private var password1 = ""
    private var password2 = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]

        binding.registerBtn.setOnClickListener {
            username = binding.uname.text.toString().trim()
            email = binding.email.text.toString().trim()
            password1 = binding.pass1.text.toString().trim()
            password2 = binding.pass2.text.toString().trim()

            viewModel.signUp(username, email, password1, password2)
        }

        viewModel.userSignUpStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    Navigation.findNavController(requireView()).navigate(R.id.toLoginFragment)
                }
                is Result.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        return binding.root
    }


}