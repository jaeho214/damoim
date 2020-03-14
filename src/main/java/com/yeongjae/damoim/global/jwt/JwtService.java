package com.yeongjae.damoim.global.jwt;

import com.yeongjae.damoim.domain.member.entity.Member;
import com.yeongjae.damoim.domain.member.exception.MemberNotFoundException;
import com.yeongjae.damoim.domain.member.repository.MemberRepository;
import com.yeongjae.damoim.global.error.exception.UserDefineException;
import com.yeongjae.damoim.global.security.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${spring.jwt.secret")
    private String SECRET_KEY;

    private final long EXPIRE_TIME = 100000 * 60 * 60;
    private final UserDetailsServiceImpl userDetailsService;
    private final MemberRepository memberRepository;

    @PostConstruct
    protected void init(){
        SECRET_KEY = Base64.getEncoder().encodeToString(SECRET_KEY.getBytes());
    }

    public String createJwt(String email){
        Map<String, Object> claimMap = new HashMap<>();
        claimMap.put("EMAIL", email);

        Date expireTime = new Date();
        expireTime.setTime(expireTime.getTime() + EXPIRE_TIME);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("issueDate", System.currentTimeMillis())
                .setClaims(claimMap)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, generateKey())
                //직렬화
                .compact();
    }

    private byte[] generateKey() {
        try{
            return SECRET_KEY.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UserDefineException("키를 변환하는 데에 실패하였습니다.");
        }
    }

    public boolean isUsable(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(generateKey())
                    .parseClaimsJws(token);
            return true;
        }catch (ExpiredJwtException e){
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request){
        return request.getHeader("token");
    }

    public String findEmailByJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(generateKey())
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("EMAIL");
    }

    public Authentication getAuthentication(String token){
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.findEmailByJwt(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public Member findMemberByToken(String token){
        String email = findEmailByJwt(token);
        return memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
    }

}
