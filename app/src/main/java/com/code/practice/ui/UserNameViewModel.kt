package com.code.practice.ui

import androidx.lifecycle.*
import com.code.practice.database.enities.UserName
import com.code.practice.repositories.UserNameRepository
import kotlinx.coroutines.launch

class UserNameViewModel(private val repository: UserNameRepository) : ViewModel() {

    var userNames = MutableLiveData<List<UserName>>()

    fun insertUserName(name: UserName) = viewModelScope.launch {
        repository.insertUserName(name)
        getAllUserNames()
    }


    fun deleteUserName(name: UserName) = viewModelScope.launch {
        repository.deleteUserName(name)
        getAllUserNames()
    }

    fun getAllUserNames() = viewModelScope.launch {
        userNames.value = repository.getAllUserNames()
    }
}
class UserNameViewModelFactory(private val repository: UserNameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserNameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserNameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}