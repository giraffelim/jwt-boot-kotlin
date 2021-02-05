package com.giraffelim.boot.config

import com.giraffelim.boot.security.interceptor.JwtTokenInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/").setViewName("main")
        registry.addViewController("/userSignUp").setViewName("userSignView")
        registry.addViewController("/login").setViewName("login")
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        // jwt 인터셉터 추가
        registry.addInterceptor(jwtTokenInterceptor())
            .addPathPatterns("/api/v1/getUserList") // 경로 지정
    }

    @Bean
    fun jwtTokenInterceptor(): JwtTokenInterceptor = JwtTokenInterceptor()

}