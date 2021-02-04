package com.giraffelim.boot.util

import com.giraffelim.boot.domain.User
import com.giraffelim.boot.domain.UserRole
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TokenUtilsTest {

    @Test
    @DisplayName("토큰 생성 테스트")
    fun 토큰생성_테스트() {
        val signUser = User(id = null, email = "sun@naver.com", pw = "12345", userRole = UserRole.ROLE_USER)
        val jwtToken = TokenUtils.generateJwtToken(signUser)
        println(jwtToken)
    }
}