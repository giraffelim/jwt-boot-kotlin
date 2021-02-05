package com.giraffelim.boot.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.giraffelim.boot.domain.User
import com.giraffelim.boot.exception.InputNotFoundException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomAuthenticationFilter(authenticationManager: AuthenticationManager) :
    UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val authRequest: UsernamePasswordAuthenticationToken

        if (!request.method.equals("POST")) {
            throw AuthenticationServiceException("Authentication method not supported: ${request.method}")
        }

        val user = ObjectMapper().readValue(request.inputStream, User::class.java)

        if (user.email.isNullOrEmpty() || user.pw.isNullOrEmpty()) {
            throw InputNotFoundException("email or password not found!")
        }

        authRequest = UsernamePasswordAuthenticationToken(user.email, user.pw)

        setDetails(request, authRequest)
        // authentication provider를 통해 authentication
        return this.authenticationManager.authenticate(authRequest)
    }

}