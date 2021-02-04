package com.giraffelim.boot.security.interceptor

import com.giraffelim.boot.constant.AuthConstants
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenInterceptor: HandlerInterceptor {

    // 컨트롤러에 요청이 가기전에 가로 챔
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val header = request.getHeader(AuthConstants.AUTH_HEADER)

        header?.let {
            return true
        }

        response.sendRedirect("/error/unauthorized")
        return false
    }

}