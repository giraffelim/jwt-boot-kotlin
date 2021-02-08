package com.giraffelim.boot.security.handler

import com.giraffelim.boot.exception.UserNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomLoginFailureHandler: AuthenticationFailureHandler {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
    ) {
        lateinit var subUrlPath: String
        when (exception) {
            is UserNotFoundException -> {
                logger.error("${exception.printStackTrace()}")
                subUrlPath = "userNotFound"
            }
        }

        val requestDispatcher = request.getRequestDispatcher("/error/$subUrlPath")
        requestDispatcher.forward(request, response)
    }

}