package com.giraffelim.boot.exception

import java.lang.RuntimeException

class UserNotFoundException(str: String): RuntimeException(str) {
}