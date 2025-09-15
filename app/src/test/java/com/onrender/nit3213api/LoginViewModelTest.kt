package com.onrender.nit3213.ui.login

import com.onrender.nit3213.data.repository.AuthRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val repo = mockk<AuthRepository>()
    private lateinit var vm: LoginViewModel

    @Before
    fun setup() {
        vm = LoginViewModel(repo)
    }

    @Test
    fun `login success updates state with keypass`() = runTest {
        coEvery { repo.login("footscray", "Lyza", "8150063") } returns Result.success("topicName")

        vm.login("footscray", "Lyza", "8150063")
        advanceUntilIdle()

        val state = vm.uiState.value
        assertTrue(state is LoginUiState.Success)
        assertEquals("topicName", (state as LoginUiState.Success).keypass)
    }

    @Test
    fun `login failure updates state with error`() = runTest {
        coEvery { repo.login(any(), any(), any()) } returns Result.failure(Exception("Invalid login"))

        vm.login("footscray", "Wrong", "0000")
        advanceUntilIdle()

        val state = vm.uiState.value
        assertTrue(state is LoginUiState.Error)
    }
}
