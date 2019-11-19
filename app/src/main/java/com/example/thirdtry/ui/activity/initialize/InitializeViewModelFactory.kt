package com.example.thirdtry.ui.activity.initialize

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class InitializeViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InitializeViewModel::class.java)) {
            return InitializeViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}