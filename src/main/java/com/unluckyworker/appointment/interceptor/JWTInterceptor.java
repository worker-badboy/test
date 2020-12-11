package com.unluckyworker.appointment.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unluckyworker.appointment.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
// JWT拦截器

@Component
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Max-Age", "86400");


        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("adminToken");
        try {
            JWTUtils.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            map.put("msg", "token错误");
        } catch (TokenExpiredException e) {
            map.put("msg", "token过期");
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "token算法不一致");
        } catch (Exception e) {
            map.put("msg", "无效签名");
        }
        map.put("state", false);

        String json = new ObjectMapper().writeValueAsString(map);



        response.getWriter().println(json);

        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        String ip = request.getHeader("x-forwarded-for");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = request.getRemoteAddr();
//        }
//        Date day = new Date();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = df.format(day);
////        Enumeration enu = request.getParameterNames();
////        StringBuffer sb = new StringBuffer();
////        while (enu.hasMoreElements()) {
////            String paraName = (String) enu.nextElement();
////            sb.append(paraName + ": " + request.getParameter(paraName) + "  ");
////        }
//        log.info("时间：{}  ip:{}  请求方法：{} ", time, ip, request.getServletPath());

    }
}
