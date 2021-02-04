package com.giraffelim.boot.service

import com.giraffelim.boot.domain.User
import java.util.*

interface UserService {

    fun findByEmail(email: String): Optional<User>

    fun signUp(user:User): User

    fun getUserList(): List<User>

}