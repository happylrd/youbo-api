package io.happylrd.youbo.config.security;

import io.happylrd.youbo.common.TokenConst;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {

    /**
     * 生成 JWT
     */
    static void addAuthentication(HttpServletResponse response, String username) {
        String jwtStr = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + TokenConst.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, TokenConst.SECRET_KEY)
                .compact();

        // 目前将 token 放在 header 中，可考虑返回为 json
        response.addHeader(TokenConst.HEADER_NAME, TokenConst.TOKEN_PREFIX + " " + jwtStr);
    }

    /**
     * 验证 JWT
     */
    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(TokenConst.HEADER_NAME);

        if (token != null) {
            // parse the token
            String username = Jwts.parser()
                    .setSigningKey(TokenConst.SECRET_KEY)
                    // 去掉前缀
                    .parseClaimsJws(token.replace(TokenConst.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            // 返回验证 token
            return username != null
                    ? new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList())
                    : null;
        }
        return null;
    }
}
