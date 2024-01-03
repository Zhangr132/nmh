package com.gcxy.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtil {

    // 创建一个秘钥
    public static final String TOKEN_KEY = "javasdfasdfasdfxixiahahahehe";
//    private static final byte[] SECRET= "zdsfot shuyixin should beajfafjafjla ".getBytes();

    // 设置token的有效期  15min
    public final static long KEEP_TIME = 15 * 60 * 60 * 1000;

    /**
     * 生成token
     * @param paramMap 用户信息
     * @return
     */
    public static String createToken(Map<String, String> paramMap) {
        Map<String,Object> headMap=new HashMap<>();
        Calendar expireTime=Calendar.getInstance();
        expireTime.add(Calendar.DATE,7);//过期时间默认7天

        JWTCreator.Builder builder=JWT.create();
        //Header(使用默认数据，故map没有值，可以省略）
        builder.withHeader(headMap);
        //Payload
        paramMap.forEach((key,value)->{
            builder.withClaim(key,value);
        });
        //过期时间
        String token=builder.withExpiresAt(expireTime.getTime())
                //Signature
                .sign(Algorithm.HMAC256(TOKEN_KEY));
        System.out.println("token到期时间：----------------------------------"+expireTime.getTime());
        return token;
    }

    /**
     * 生成token
     * @param accountName  用户名
     * @param userId
     * @return token
     */
    public static String buildJwt(String accountName,String userId){
        Date date = new Date(System.currentTimeMillis() + KEEP_TIME);
        Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY);
        // 设置头部信息
        Map header = new HashMap<>(2);
        header.put("typ","JWT");
        header.put("alg","HS256");
        return JWT.create()
                .withHeader(header)
                .withClaim("loginName",accountName)
                .withClaim("userId",userId)
                .withExpiresAt(date)
                .sign(algorithm);
    }


    /**
     * 校验token是否正确
     * 如果验证失败，会抛出相应的异常，否则什么也不做
     * @param token
     * @return
     */
    public static void verify(String token) {
        JWT.require(Algorithm.HMAC256(TOKEN_KEY)).build().verify(token);
    }
//    public static boolean verify(String token) {
//        try {
//            Algorithm algorithm = Algorithm.HMAC256(TOKEN_KEY);
//            JWTVerifier verifier = JWT.require(algorithm)
//                    .build();
//            DecodedJWT jwt = verifier.verify(token);
//            return true;
//        } catch (Exception exception) {
//            return false;
//        }
//    }

    /**
     * 获取token信息
     * @param token
     * @return
     */
    public static DecodedJWT getTokenInfo(String token) {
        return JWT.require(Algorithm.HMAC256(TOKEN_KEY)).build().verify(token);
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("loginName").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }
    /**
     * 获取登陆用户ID
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获取账号名和用户名
     * @param token
     * @return
     */
    public static Map<String,String> getUserInfo(String token){
        Map<String, String> userInfo = new HashMap<>();
        try {
            DecodedJWT jwt = JWT.decode(token);
            userInfo.put("account", jwt.getClaim("account").asString());
            userInfo.put("accName", jwt.getClaim("accName").asString());
            return userInfo;
        } catch (JWTDecodeException e) {
            return null;
        }
    }
}
