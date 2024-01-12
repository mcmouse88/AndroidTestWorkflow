package com.mcmouse88.hrenapplication

import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.savedstate.SavedStateRegistryOwner
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(
    private val noteRepository: NoteRepository,
    private val handle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val KEY = "ttt"
    }

    val title = handle.getStateFlow(KEY, "EMPTY")
    // val title: LiveData<String> get () = _title

    fun changeStr() {
        // _title.value = "SAVED"
        handle[KEY] = "SAVED"
        viewModelScope.launch {
            noteRepository.longOperation()
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.e("TAG_CHECK", "onCleared")
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun bindNoteRepository(impl: NoteRepositoryImpl): NoteRepository
}