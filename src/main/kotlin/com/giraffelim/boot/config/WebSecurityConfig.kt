package com.giraffelim.boot.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    // 정적자원의 경우 시큐리티 설정을 적용하지 않음.
    override fun configure(web: WebSecurity) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
    }

    override fun configure(http: HttpSecurity) {
        http
            .headers().frameOptions().sameOrigin() // 동일 도메인에서는 iframe 접근이 가능하도록 설정
            .and()
            .csrf().disable().authorizeRequests()
            .anyRequest().permitAll() // 토큰을 활용하는 경우 모든 요청에 대한 접근을 허용
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰을 활용하면 세션이 필요없으므로 세션을 사용하지 않음
            .and()
            .formLogin().disable() // 폼 기반의 로그인 비활성화
    }

    @Bean
    fun bCryptPasswordEncoder():BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}