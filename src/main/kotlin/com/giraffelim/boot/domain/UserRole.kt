package com.giraffelim.boot.domain

enum class UserRole(val value: String) {

    // TODO GrantedAuthority 상속해서 구현

    ROLE_ADMIN("ROLE_USER"),
    ROLE_USER("ROLE_ADMIN")
}
