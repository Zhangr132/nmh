package com.gcxy.config.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcxy.config.R;
import com.gcxy.utils.JwtTokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        String msg = null;
        try{
            JwtTokenUtil.verify(token);
        }catch (SignatureVerificationException e){
            msg="签名无效";
        }catch (TokenExpiredException e){
            msg="签名过期";
        }catch (AlgorithmMismatchException e){
            msg="算法不一致";
        }catch (Exception e){
            msg="token无效";
        }
        String error=new ObjectMapper().writeValueAsString(R.Failed(msg));
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(error);
        return false;
    }
}
