package com.giraffelim.boot.service

import com.giraffelim.boot.domain.MyUserDetails
import com.giraffelim.boot.exception.UserNotFoundException
import com.giraffelim.boot.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service("userDetailService")
class UserDetailServiceImpl(val userRepository: UserRepository): UserDetailsService {

    // 유저 조회(이때 유저는 UserDetails를 상속 받은 유저여야 한다.)
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByEmail(username).map{ u -> MyUserDetails(u) }.orElseThrow { UserNotFoundException("$username is not found !!!") }
    }

}