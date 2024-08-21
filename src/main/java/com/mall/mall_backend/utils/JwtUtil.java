package com.mall.mall_backend.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //有效期为
    public static final Long JWT_TTL = 60 * 60 *1000L;// 60 * 60 *1000  一个小时
    //设置秘钥明文
    public static final String JWT_KEY = "zcjjjj";//even length,encoded base64 string, all consist of ascii

    public static String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }

    /**
     * 生成jtw  jwt加密
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw  jwt加密
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }


    /**
     * 创建token jwt加密
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;   //header
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("sg")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥 ，，，，，sign and header
                .setExpiration(expDate);
        //.claim("username", "username123") // Stores the username as a custom claim

    }



    public static void main(String[] args) throws Exception {
        //jwt加密
        String jwt = createJWT("userid");

        //jwt解密
        Claims claims = parseJWT(jwt);
        String subject = claims.getSubject();



        System.out.println(subject);
        System.out.println(jwt);
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     * Base64 Decoding: It decodes the string JWT_KEY from Base64 to get a byte array.
     * SecretKey Generation: It uses the SecretKeySpec class to convert the byte array into a SecretKey object. The SecretKeySpec specifies the key and the algorithm (in this case, "AES").
     * Using "qx" Directly: If you choose to use "qx" directly, you can skip the Base64 encoding/decoding step and directly create a SecretKey using the raw string:
     *
     * String rawKey = "qx";
     * SecretKey key = new SecretKeySpec(rawKey.getBytes(), "HmacSHA256");
     *Security Implications: The strength of the secret key is crucial for the security of the JWT. A very short key like "qx" is weak and can be easily brute-forced. It's recommended to use a more complex, longer key for better security.
     *
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * jwt解密
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
        /*
        String username = claims.getSubject();  // If stored in the "sub" claim
// Or, if stored as a custom claim
String username = claims.get("username", String.class);
         */


    }


}
