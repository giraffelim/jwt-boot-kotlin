package com.giraffelim.boot.service

import com.giraffelim.boot.domain.User
import com.giraffelim.boot.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service("userService")
class UserServiceImpl(val userRepository: UserRepository): UserService {

    override fun findByEmail(email: String): Optional<User> {
        return userRepository.findByEmail(email)
    }

    override fun signUp(user: User): User {
        return userRepository.save(user)
    }

    override fun getUserList(): List<User> {
        return userRepository.findAll()
    }
}