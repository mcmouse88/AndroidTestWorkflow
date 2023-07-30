package com.mcmouse88.hrenapplication.entity

import android.graphics.Color
import com.mcmouse88.hrenapplication.UserDto
import com.mcmouse88.hrenapplication.UserEntity
import com.mcmouse88.hrenapplication.UserStatus
import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UserDtoTest {

    @Before
    fun setUp() {
        mockkStatic(Color::class)
    }

    @After
    fun tearDown() {
        unmockkStatic(Color::class)
    }

    @Test
    fun `test mapping UserDto to UserEntity`() {
        val dto = UserDto(
            id = 1,
            status = "Active",
            statusColor = "#FF0000"
        )
        every { Color.parseColor("#FF0000") } returns Color.RED
        val entity = dto.userDtoToUserEntity()
        val expected = UserEntity(
            id = 1,
            status = UserStatus.Active,
            color = Color.RED
        )
        Assert.assertEquals(expected, entity)
    }
}