package com.shing100.community.module.user.jwt;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        //필요한 권한이 없이 접근하려 할때 403
        HashMap<Object, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_FORBIDDEN);
        body.put("message", "권한이 없습니다.");
        body.put("path", request.getRequestURI());
        body.put("timestamp", System.currentTimeMillis());
        response.setContentType("application/json;charset=UTF-8");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, body.toString());
    }
}
