package com.giraffelim.boot.web

import com.giraffelim.boot.domain.User
import com.giraffelim.boot.domain.UserRole
import com.giraffelim.boot.service.UserService
import com.giraffelim.boot.util.TokenUtils
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class UserController(val bCryptPasswordEncoder: BCryptPasswordEncoder, val userService: UserService) {

    @PostMapping("/userSignUp")
    fun signUp(@RequestBody user: User): ResponseEntity<Any> {
        user.pw = bCryptPasswordEncoder.encode(user.pw)
        user.userRole = UserRole.ROLE_USER
        return if (userService.findByEmail(user.email).isPresent) {
            ResponseEntity.badRequest().build()
        } else {
            ResponseEntity.ok().body(TokenUtils.generateJwtToken(userService.signUp(user)))
        }
    }

    @GetMapping("/getUserList")
    fun getUserList(): List<User> {
        return userService.getUserList()
    }
}