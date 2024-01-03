//package com.gcxy.config.interceptor;
//
//import com.gcxy.utils.JwtTokenUtil;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.HashMap;
//import java.util.Map;
//
//public class TokenInterceptor implements HandlerInterceptor {
//    /**
//     * 将cookie封装到Map里面
//     *
//     * @param request
//     * @return
//     */
//    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
//        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
//        Cookie[] cookies = request.getCookies();
//        System.out.println(cookies);
//        if (null != cookies) {
//            for (Cookie cookie : cookies) {
//                cookieMap.put(cookie.getName(), cookie);
//            }
//        }
//        System.out.println(cookieMap);
//        return cookieMap;
//    }
//
//    /**
//     * 根据名字获取cookie
//     *
//     * @param request
//     * @param name    cookie名字
//     * @return
//     */
//    public static Cookie getCookieByName(HttpServletRequest request, String name) {
//        Map<String, Cookie> cookieMap = ReadCookieMap(request);
//        if (cookieMap.containsKey(name)) {
//            Cookie cookie =  cookieMap.get(name);
//            return cookie;
//        } else {
//            return null;
//        }
//    }
//
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Cookie cookie=getCookieByName(request,"TOKEN");
//        response.setCharacterEncoding("utf-8");
//        System.out.println("========>"+cookie.getName());
//        //如果已经登录，不拦截
//        if (null != cookie) {
//            //验证token是否正确
//            boolean result = JwtTokenUtil.verify(cookie.getName());
//            if (!result) {
//                return false;
//            }
//            return true;
//        }
//        //如果没有登录，则跳转到登录界面
//        else {
//            //重定向 第一种 调用控制器 方法
////            response.sendRedirect(request.getContextPath() + "/login");
//            //重定向 第二种 重定向方法
//            //            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
//            //            System.out.println(request.getContextPath());
//            return false;
//            /**
//             * 以下是为了登录成功后返回到刚刚的操作，不跳到主界面
//             * 实现：通过将请求URL保存到session的beforePath中，然后在登录时判断beforePath是否为空
//             */
//        }
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//    }
//
//}
