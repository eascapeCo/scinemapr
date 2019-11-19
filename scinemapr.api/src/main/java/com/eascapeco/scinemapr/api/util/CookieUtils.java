package com.eascapeco.scinemapr.api.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 클래스명: <code>CookieUtils</code>
 *
 * <pre>
 *  cookie를 처리하는 유틸
 * </pre>
 *
 * @author JaeHan-Kim
 * @date 2019. 11. 19.
 *
 */
public class CookieUtils {
    
    /**
     * 쿠키 생성
     * 
     * @param name
     * @param value
     * @param maxAge
     * @param response
     */
    public static void setCookie(String name, String value, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }
    
    /**
     * 쿠키 생성
     * 
     * @param name
     * @param value
     * @param response
     */
    public static void setCookie(String name, String value, HttpServletResponse response) {
        setCookie(name, value, -1, response);
    }
    
    public static String getCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String value = null;
        
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (StringUtils.equals(name, c.getName())) {
                    value = c.getValue();
                    break;
                }
            }
        }
        
        return value;
    }
}
