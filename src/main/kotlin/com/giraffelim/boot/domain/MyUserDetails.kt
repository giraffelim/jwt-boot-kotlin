package com.giraffelim.boot.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class MyUserDetails(val user: User): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = ArrayList<GrantedAuthority>()
        authorities.add(user.userRole!!)
        return authorities
    }

    override fun getPassword(): String {
        return user.pw
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }

}