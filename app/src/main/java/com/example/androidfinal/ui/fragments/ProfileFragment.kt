package com.example.androidfinal.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import com.example.androidfinal.R
import com.example.androidfinal.databinding.FragmentProfileBinding
import com.example.androidfinal.models.User
import com.example.androidfinal.session.LoginPrefs
import com.example.androidfinal.ui.activity.MainActivity
import com.example.androidfinal.viewModel.ProfileViewModel
import com.example.androidfinal.utils.Result

class ProfileFragment : Fragment(R.layout.fragment_profile) {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
//        (activity as MainActivity).setMenu(R.menu.profile_menu)


        observe()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Navigation.findNavController(requireView()).navigate(R.id.backToMainPage)
            }
        })

        return binding.root
    }

    fun observe() {
        viewModel.getUserInfo("6RqKBh0cvDW99UXQ1IqJ5zh8fIo2")
        viewModel.user.observe(viewLifecycleOwner) {state ->
            when(state) {
                is Result.Loading -> {
                    (activity as MainActivity).showProgressBar()
                }
                is Result.Error -> {
                    Log.d("TAG", "Error")
                }
                is Result.Success -> {
                    setProfile(state.data!!)
                    (activity as MainActivity).hideProgressBar()
                }
            }

        }

    }

    private fun setProfile(user: User) {
        binding.username.text = user.username
        binding.realName.text = user.first_name + " " + user.last_name
        binding.email.text = user.email
        binding.games.text = user.purchasedGame?.size.toString()
    }
}