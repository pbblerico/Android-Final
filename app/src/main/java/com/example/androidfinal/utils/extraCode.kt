package com.example.androidfinal.utils

class extraCode {
}

//        viewModel.getUserList()
//        viewModel.users.observe(viewLifecycleOwner) { state ->
//            when(state){
//                is Result.Loading -> {
//                    Log.d("TAG", "Loading")
//                    act.showProgressBar()
//                }
//                is Result.Error -> {
//                    Log.d("TAG", "Error")
//                }
//                is Result.Success -> {
//                    state.data?.forEach { it ->
//                        Log.d("TAG", it.toString())
//                    }
//                    act.hideProgressBar()
//
//                }
//            }
//        }
//
//        viewModel.getGenreList()
//        viewModel.genres.observe(viewLifecycleOwner) { state ->
//            when(state){
//                is Result.Loading -> {
//                    Log.d("TAG", "Loading")
//                    act.showProgressBar()
//                }
//                is Result.Error -> {
//                    Log.d("TAG", "Error")
//                }
//                is Result.Success -> {
//                    state.data?.forEach { it ->
//                        Log.d("TAG", it.toString())
//                    }
//                    act.hideProgressBar()
//
//                }
//            }
//        }

//        viewModel.addGenre("new genre")
//        viewModel.addGenre.observe(viewLifecycleOwner) { state ->
//            when(state){
//                is Result.Loading -> {
//                    Log.d("TAG", "Loading")
//                    act.showProgressBar()
//                }
//                is Result.Error -> {
//                    Log.d("TAG", "Error")
//                }
//                is Result.Success -> {
//                    state.data?.forEach { it ->
//                        Log.d("TAG", it.toString())
//                    }
//                    act.hideProgressBar()
//
//                }
//            }
//        }
//
//        viewModel.deleteGenre("new genre")
//        viewModel.deleteGenre.observe(viewLifecycleOwner) { state ->
//            when(state){
//                is Result.Loading -> {
//                    Log.d("TAG", "Loading")
//                    act.showProgressBar()
//                }
//                is Result.Error -> {
//                    Log.d("TAG", "Error")
//                }
//                is Result.Success -> {
//                    state.data?.forEach { it ->
//                        Log.d("TAG", it.toString())
//                    }
//                    act.hideProgressBar()
//
//                }
//            }
//        }