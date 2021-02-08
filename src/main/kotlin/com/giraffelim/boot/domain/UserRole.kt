package com.giraffelim.boot.domain

import org.springframework.security.core.GrantedAuthority

enum class UserRole(val key: String, val value: String): GrantedAuthority {

    ADMIN("ROLE_ADMIN", "관리자") {
        override fun getAuthority(): String {
            return this.value
        }
    },
    ROLE_USER("ROLE_USER", "유저") {
        override fun getAuthority(): String {
            return this.value
        }
    }
}
