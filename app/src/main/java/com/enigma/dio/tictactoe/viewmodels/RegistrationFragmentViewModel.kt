package com.enigma.dio.tictactoe.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enigma.dio.tictactoe.utils.ResourceState
import com.enigma.dio.tictactoe.utils.ResourceStatus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegistrationFragmentViewModel : ViewModel() {

    private var _allNameValid = MutableLiveData<ResourceState>()

    val allNameValid : LiveData<ResourceState>
        get() {
            return _allNameValid
        }

    fun inputNamesValidation(p1 : String, p2 : String) {

        GlobalScope.launch {
            _allNameValid.postValue(ResourceState.loading())
            delay(2000)
            if (p1.isNotEmpty() && p2.isNotEmpty()) {
                _allNameValid.postValue(ResourceState.success(true))
            } else {
                _allNameValid.postValue(ResourceState.failure("All player name must be inserted!"))
            }

//            if (p1.isEmpty()) {
//                allNameValid.postValue(ResourceState.failure("Player 1 name must be inserted!"))
//            }
//            if (p2.isEmpty()) {
//                allNameValid.postValue(ResourceState.failure("Player 2 name must be inserted<1"))
//            }
        }
    }
}