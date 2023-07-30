package com.mcmouse88.hrenapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val noteRepository: NoteRepository
) : ViewModel() {

    val notesStateFlow = MutableStateFlow<List<Note>>(emptyList())
    val commands = MutableSharedFlow<Commands>(replay = 0, extraBufferCapacity = 1)
    val shimmerFlow = MutableStateFlow(true)

    init {
        viewModelScope.launch {
            try {
                val list = noteRepository.getPost()
                notesStateFlow.tryEmit(list)
            } catch (e: Exception) {
                when (e) {
                    is NetworkException -> commands.tryEmit(Commands.ShowSnackbar("Network Error"))
                    else -> commands.tryEmit(Commands.ShowBottomSheet("Unknown Error"))
                }
            }
            finally {
                shimmerFlow.tryEmit(false)
            }
        }
    }

    sealed interface Commands {
        class ShowBottomSheet(val message: String) : Commands
        class ShowSnackbar(val message: String) : Commands
    }
}