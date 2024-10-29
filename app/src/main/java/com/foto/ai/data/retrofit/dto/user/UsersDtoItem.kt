package com.foto.ai.data.retrofit.dto.user

import com.foto.ai.data.room.dto.UserDto
import com.foto.ai.domain.model.User

data class UsersDtoItem(
    val id: String,
    val name: String,
    val email: String,
    val image: String,
    val createdAt: String,
    val googleId: String
)

fun UsersDtoItem.toUser(): User = this.let {
    User(
        id = it.id,
        name = it.name,
        email = it.email,
        image = it.image,
        createdAt = it.createdAt,
        googleId = it.googleId
    )
}

fun UsersDtoItem.toUserDto(): UserDto = this.let {
    UserDto(
        id = it.id,
        name = it.name,
        email = it.email,
        image = it.image,
        createdAt = it.createdAt,
        googleId = it.googleId
    )
}