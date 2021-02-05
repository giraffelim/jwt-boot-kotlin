package com.giraffelim.boot.util

import com.giraffelim.boot.domain.User
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import java.lang.NullPointerException
import java.security.Key
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.xml.bind.DatatypeConverter
import kotlin.collections.HashMap

/**
 *  JWT는 Json 포맷을 이용하여 사용자에 대한 속성을 저장하는 Claim 기반의 Web Token이다.
 *  JWT는 토큰 자체를 정보로 사용하는 Self-Contained 방식으로 정보를 안전하게 전달한다.
 */
class TokenUtils {

    companion object {

        private val logger = LoggerFactory.getLogger(this.javaClass)

        private const val secretKey = "JWT-SECRET-KEY"

        fun generateJwtToken(user: User): String {
            return Jwts.builder()
                .setSubject(user.email)
                .setHeader(createHeader())
                .setClaims(createClaims(user))
                .setExpiration(createExpireDate())
                .signWith(SignatureAlgorithm.HS256, createSigningKey())
                .compact()
        }

        /**
         *  토큰 검증
         */
        fun isValidToken(token: String): Boolean {
            try {
                val claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretKey)).parseClaimsJws(token).body
                logger.info("expire Time: ${claims.expiration}")
                logger.info("email: ${claims["email"]}")
                logger.info("role: ${claims["role"]}")
                return true
            } catch (e: ExpiredJwtException) {
                logger.error("Token Expired")
                return false
            } catch (e: JwtException) {
                logger.error("Token Tampered")
                return false
            } catch (e: NullPointerException) {
                logger.error("Token is NULL")
                return false;
            }
        }

        /**
         *  헤더 생성
         *  토큰의 헤더는 typ과 alg 두 가지 정보로 구성된다.
         *  alg는 헤더(Header)를 암호화 하는 것이 아니고, signature를 해싱하기 위한 알고리즘을 지정
         */
        private fun createHeader(): Map<String, Any> {
            return hashMapOf("alg" to "HS256", "typ" to "JWT", "regDate" to System.currentTimeMillis())
        }

        /**
         *  클레임 생성
         *  토큰의 페이로드에는 토큰에서 사용할 정보의 조각들인 클레임(Claims)이 담겨있다.
         */
        private fun createClaims(user: User): HashMap<String, Any?> {
            // 공개 클레임에 사용자의 이름과 역할을 설정하여 정보를 조회할 수 있다.
            // 공개 클레임은 사용자 정의 클레임으로, 공개용 정보를 위해 사용된다.
            return hashMapOf("email" to user.email, "role" to user.userRole)
        }

        /**
         *  토큰 만료기간 설정
         */
        private fun createExpireDate(): Date {
            // 토큰 만료기간은 30일로 지정
            val c = Calendar.getInstance()
            c.add(Calendar.DATE, 30)
            return c.time
        }

        /**
         *  secretKey 생성
         */
        private fun createSigningKey(): Key {
            return SecretKeySpec(DatatypeConverter.parseBase64Binary(secretKey), SignatureAlgorithm.HS256.jcaName)
        }

        /**
         *  토큰 추출
         */
        fun getTokenFromHeader(header: String): String {
            return header.split(" ")[1]
        }

    }

}