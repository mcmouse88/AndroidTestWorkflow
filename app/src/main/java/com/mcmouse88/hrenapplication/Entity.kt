package com.mcmouse88.hrenapplication

import android.graphics.Color

data class UserDto(
    val id: Long,
    val status: String,
    val statusColor: String
) {
    fun userDtoToUserEntity(): UserEntity {
        return UserEntity(
            id = this.id,
            status = UserStatus.parse(this.status),
            color = Color.parseColor(this.statusColor)
        )
    }
}

data class UserEntity(
    val id: Long,
    val status: UserStatus,
    val color: Int
)

enum class UserStatus(val status: String) {
    Active("Active"), Banned("Banned");

    companion object {
        fun parse(value: String): UserStatus = values().find { it.status == value } ?: Banned
    }
}

