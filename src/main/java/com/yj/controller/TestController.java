package com.yj.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yj.dao.UserDao;
import com.yj.entity.User;
import com.yj.service.UserService;
import com.yj.utils.JWTConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Map<String,Object> login(User user){
        Map<String, Object> map = new HashMap<>();

        log.info("用户名：[{}]", user.getUsername());
        log.info("密码：[{}]", user.getPassword());
        Map<String,String> payload = new HashMap<>();

        try {
            User userDB = userService.login(user);
            payload.put("id", userDB.getId());
            payload.put("username", userDB.getUsername());

            String token = JWTConfig.getToken(payload);

            map.put("state", true);
            map.put("msg","认证通过！");
            map.put("token",token);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("state", false);
            map.put("msg",e.getMessage());
        }
        return map;
    }


    @RequestMapping("/test")
    public Map<String,Object> test(HttpServletRequest request){
        String token = request.getHeader("token");
        DecodedJWT verify = JWTConfig.verify(token);
        String id = verify.getClaim("id").asString();
        String username = verify.getClaim("username").asString();
        log.info("用户id：[{}]", id);
        log.info("用户username：[{}]", username);
        Map<String, Object> map = new HashMap<>();
        map.put("state",true);
        map.put("msg","请求成功！");


        return map;
    }
}
