package ui

import Configuration
import androidx.compose.runtime.mutableStateOf

class NoteViewModel {

    private var _screenState = mutableStateOf<Configuration>(Configuration.Screen1)
    val screenState = _screenState.value

    fun changeScreenState(configuration: Configuration){
        _screenState.value = configuration
    }

}