package com.giraffelim.boot.security.handler

import com.giraffelim.boot.constant.AuthConstants
import com.giraffelim.boot.domain.MyUserDetails
import com.giraffelim.boot.util.TokenUtils
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CustomLoginSuccessHandler: SavedRequestAwareAuthenticationSuccessHandler() {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val user = (authentication.principal as MyUserDetails).user
        val jwtToken = TokenUtils.generateJwtToken(user)
        val responseCookie = Cookie(AuthConstants.AUTH_HEADER, "${AuthConstants.TOKEN_TYPE} $jwtToken")
        responseCookie.path = "/"
        responseCookie.maxAge = 60 * 60 * 24 * 30
        response.addCookie(responseCookie)
    }
}