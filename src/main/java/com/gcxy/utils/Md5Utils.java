/*
package com.gcxy.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Md5Utils {
    //实现一个md5加密
    private static final Logger log= LoggerFactory.getLogger(Md5Utils.class);

    */
/**
     *
     * @param s 明文密码
     * @return
     * @throws  Exception
     *//*

    private static byte[] md5(String s){
        MessageDigest messageDigest;
        try {
            messageDigest=MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(s.getBytes(StandardCharsets.UTF_8));
            byte[] ms= messageDigest.digest();
            return ms;
        }catch (Exception e){
            log.error("MD5 Error...",e);
        }
        return null;
    }
    public static final String toHex(byte hash[]){
        if (hash==null){
            return null;
        }
        StringBuffer buf=new StringBuffer(hash.length*2);
        int i;

        for(i=0;i< hash.length;i++){
            if((hash[i]&0xff)<0x10){
                buf.append("0");
            }
            buf.append(Long.toString(hash[i]&0xff,16));
        }
        return buf.toString();
    }

    public static String hash(String s){
        try {
            return new String(toHex(md5(s)).getBytes("UTF-8"),"UTF-8");
        }
        catch (Exception e){
            log.error("not supported charset......{}",e);
            return s;
        }
    }
}
*/
