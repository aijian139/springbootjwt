package com.yj;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

//@SpringBootTest
class SpringbootjwtApplicationTests {

    @Test
    void contextLoads() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 60);


        String token = JWT.create().withClaim("userid", 21)
                .withClaim("username", "yijian")
//                .withExpiresAt(calendar.getTime())  // 指定过期时间
                .sign(Algorithm.HMAC256("qwer123")); // 签名

        System.out.println(token);

    }

    @Test
    public void require(){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("qwer123")).build();
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyaWQiOjIxLCJ1c2VybmFtZSI6InlpamlhbiJ9.FdzaY0_Q0MBKbO-QF7T-tEVHU4xrF0rL_xEK95wkx0c");
        Integer userid = verify.getClaim("userid").asInt();
        String username = verify.getClaim("username").asString();
        System.out.println(userid+" "+username);
    }

}
