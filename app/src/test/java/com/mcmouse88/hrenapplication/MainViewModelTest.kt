package com.mcmouse88.hrenapplication

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert
import org.junit.Test

class MainViewModelTest : ViewModelTest() {

    @Test
    fun `listNote emits list`() = runTest {
        val repository = mockk<NoteRepository>()
        coEvery { repository.getPost() } returns createNoteList()
        val viewModel = MainViewModel(repository)

        launch {
            viewModel.notesStateFlow.test {
                Assert.assertEquals(createNoteList(), awaitItem())
                awaitComplete()
            }
        }

        launch {
            viewModel.shimmerFlow.test {
                Assert.assertEquals(false, awaitItem())
                awaitComplete()
            }
        }
    }

    @Test
    fun `commands emits ShowBottomSheet when repository throws exception`() = runTest {
        val repository = mockk<NoteRepository>()
        coEvery { repository.getPost() } throws UnknownException(IllegalStateException())
        val viewModel = MainViewModel(repository)

        val job = launch {
            viewModel.commands.test {
                Assert.assertEquals(MainViewModel.Commands.ShowSnackbar("Unknown Error"), awaitItem())
                cancelAndConsumeRemainingEvents()
            }
        }

        launch {
            viewModel.shimmerFlow.test {
                Assert.assertEquals(false, awaitItem())
                awaitComplete()
            }
        }

        job.cancelAndJoin()
    }

    @Test
    fun `commands emits ShowBottomSheet when repository throws NetworkException`() = runTest {
        val repository = mockk<NoteRepository>()
        coEvery { repository.getPost() } throws NetworkException(IOException())
        val viewModel = MainViewModel(repository)

        launch {
            viewModel.commands.test {
                Assert.assertEquals(MainViewModel.Commands.ShowBottomSheet("Unknown Error"), awaitItem())
                awaitComplete()
            }
        }
        launch {
            viewModel.shimmerFlow.test {
                Assert.assertEquals(false, awaitItem())
                awaitComplete()
            }
        }
    }

    private fun createNoteList(): List<Note> {
        return listOf(Note("title", "description"))
    }
}