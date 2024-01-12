package com.mcmouse88.hrenapplication

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {

    single<NoteRepository> {
        NoteRepositoryImpl()
    }

    viewModel {
        MainViewModel(
            noteRepository = get(),
            handle = get()
        )
    }
}