package com.giraffelim.boot.web.error

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/error")
class ErrorController {

    @GetMapping("/unauthorized")
    fun unauthorized(): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED!")
    }

    @PostMapping("/userNotFound")
    fun userNotFound(request: HttpServletRequest, response: HttpServletResponse): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("가입되지 않은 유저입니다.")
    }
}