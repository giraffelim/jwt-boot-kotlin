package com.giraffelim.boot.domain

import javax.persistence.*

@Entity
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val email: String,
    var pw: String, // 암호화를 위해 mutable 변수로 선언
    @Enumerated(EnumType.STRING)
    var userRole: UserRole?
)