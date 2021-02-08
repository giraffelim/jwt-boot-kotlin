package com.giraffelim.boot.exception

import org.springframework.security.core.AuthenticationException


class UserNotFoundException(str: String): AuthenticationException(str) {
}