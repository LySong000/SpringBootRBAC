package com.lysong.friday.security.authentication;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: LySong
 * @Date: 2020/3/20 23:26
 */
@Component
public class RestAuthenticationAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setStatus(httpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=UTF8");
        httpServletResponse.sendRedirect("/403.html");
    }
}
