package com.giraffelim.boot.repository

import com.giraffelim.boot.domain.User
import com.giraffelim.boot.domain.UserRole
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.annotation.Rollback
import kotlin.NoSuchElementException

@DataJpaTest
class UserRepositoryTest(
    @Autowired
    val userRepository: UserRepository
) {

    val bCryptPasswordEncoder = BCryptPasswordEncoder()

    @AfterEach
    fun deleteAll() = userRepository.deleteAll()

    @Test
    @DisplayName("유저 회원가입 테스트")
    @Rollback(false)
    fun 유저_회원가입() {
        val signUser = User(id = null, email = "sun@naver.com", pw = bCryptPasswordEncoder.encode("12345"), userRole = UserRole.ROLE_USER)
        userRepository.save(signUser)

        val userList = userRepository.findAll()

        val findUser = userList[0]
        assertThat(signUser.id).isEqualTo(findUser.id)
        assertThat(signUser.pw).isEqualTo(findUser.pw)
    }

    @Test
    @DisplayName("이메일을 통한 유저 조회 테스트")
    @Rollback(false)
    fun 이메일_유저조회() {
        val signUser = User(id = null, email = "sun@naver.com", pw = bCryptPasswordEncoder.encode("12345"), userRole = UserRole.ROLE_USER)
        userRepository.save(signUser)

        val optionalUser = userRepository.findByEmail(signUser.email)

        assertThat(signUser.email).isEqualTo(optionalUser.orElseThrow { NoSuchElementException() }.email)
    }

}