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
import com.example.androidfinal.databinding.FragmentLoginBinding
import com.example.androidfinal.session.LoginPrefs
import com.example.androidfinal.viewModel.LoginViewModel
import com.example.androidfinal.utils.Result

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    private lateinit var email: String
    private lateinit var password: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.loginBtn.setOnClickListener {
            this.email = binding.uname.text.toString()
            this.password = binding.pass.text.toString()

            viewModel.login(email, password)

        }

        viewModel.userLoginStatus.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Loading -> {
                    Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Result.Success -> {
                    viewModel.createLoginSession(requireContext(), email, password)
                    Navigation.findNavController(requireView()).navigate(R.id.toMainPageFragment)
                }
                is Result.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
//        binding.button2.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.toSignupFragment)
//        }
//        binding.button.setOnClickListener {
//            Navigation.findNavController(it).navigate(R.id.toMainPageFragment)
//        }
        return binding.root
    }


}