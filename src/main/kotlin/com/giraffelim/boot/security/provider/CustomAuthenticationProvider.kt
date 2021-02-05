package com.giraffelim.boot.security.provider

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.annotation.Resource

class CustomAuthenticationProvider(private val bCryptPasswordEncoder: BCryptPasswordEncoder): AuthenticationProvider {

    @Resource(name = "userDetailService")
    private lateinit var userDetailsService: UserDetailsService

    private val logger = LoggerFactory.getLogger(this.javaClass)

    /**
     *  인증 과정은 해당 메서드를 통해 진행
     */
    override fun authenticate(authentication: Authentication): Authentication {
        lateinit var token: Authentication
        if (authentication is UsernamePasswordAuthenticationToken) {
            token = authentication
        }

        val email = token.name
        val pw = token.credentials as String
        // 이메일로 유저 조회
        val myUserDetails = userDetailsService.loadUserByUsername(email)

        if(!bCryptPasswordEncoder.matches(pw, myUserDetails.password)) {
            throw BadCredentialsException("${myUserDetails.username} invalid password")
        }

        return UsernamePasswordAuthenticationToken(myUserDetails, pw, myUserDetails.authorities)
    }

    /**
     *  필터에서 보내준 Authentication 객체를 이 Authentication Provider가
     *  인증가능한지 클래스인지 확인하는 메서드
     *  해결 가능하면 TURE를 리턴
     *  */
    override fun supports(authentication: Class<*>): Boolean {
        return authentication == UsernamePasswordAuthenticationToken::class.java
    }

}