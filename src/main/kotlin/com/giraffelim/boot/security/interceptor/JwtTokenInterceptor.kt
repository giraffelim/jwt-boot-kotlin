package com.giraffelim.boot.security.interceptor

import com.giraffelim.boot.constant.AuthConstants
import com.giraffelim.boot.util.TokenUtils
import org.springframework.web.servlet.HandlerInterceptor
import org.yaml.snakeyaml.util.UriEncoder
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenInterceptor : HandlerInterceptor {

    // 컨트롤러에 요청이 가기전에 가로 챔
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val header = request.getHeader(AuthConstants.AUTH_HEADER)
        val cookie = request.cookies

        header?.let {
            val tokenFromHeader = TokenUtils.getTokenFromHeader(it)
            return TokenUtils.isValidToken(tokenFromHeader)
        }

        cookie?.let {
            val tokenFromCookie = TokenUtils.getTokenFromHeader(UriEncoder.decode(it[0].value))
            return TokenUtils.isValidToken(tokenFromCookie)
        }


        response.sendRedirect("/error/unauthorized")
        return false
    }

}