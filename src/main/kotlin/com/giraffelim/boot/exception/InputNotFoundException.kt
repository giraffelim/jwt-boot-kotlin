package com.giraffelim.boot.exception

import java.lang.RuntimeException

class InputNotFoundException(str: String): RuntimeException(str) {

}