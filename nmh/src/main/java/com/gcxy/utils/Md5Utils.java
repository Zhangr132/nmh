//package com.gcxy.utils;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.nio.charset.StandardCharsets;
//import java.security.MessageDigest;
//
//public class Md5Utils {
//    private static final Logger log= LoggerFactory.getLogger(Md5Utils.class);
//    private static byte[] md5(String s){
//        MessageDigest messageDigest;
//        try {
//            messageDigest=MessageDigest.getInstance("MD5");
//            messageDigest.reset();
//            messageDigest.update(s.getBytes(StandardCharsets.UTF_8));
//            byte[] ms= messageDigest.digest();
//            return ms;
//        }catch (Exception e){
//            return null;
//        }
//
//    }
//}
