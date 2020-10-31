package com.yj.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.cglib.beans.BulkBean;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JWTConfig {

    private final static String sign = "!qwer123@";


    // 获取令牌 token
    public static String getToken(Map<String,String> map){

        // 设置过期时间，默认7天
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 7);

//        Date date = new Date();
//
//        Date expireDate = new Date(date.getTime()+100);

        JWTCreator.Builder builder = JWT.create();
        builder.withExpiresAt(instance.getTime());


        // 添加payload 负载信息
        map.forEach((k,v)->{
            builder.withClaim(k, v);
        });
        // 设置签名信息
        String token = builder.sign(Algorithm.HMAC256(JWTConfig.sign));

        return token;

    }


    //验证令牌 token
    public static DecodedJWT verify(String token){

        Verification require = JWT.require(Algorithm.HMAC256(sign));
        JWTVerifier verifier = require.build();
        DecodedJWT verify = verifier.verify(token);

        return verify;
    }


}
