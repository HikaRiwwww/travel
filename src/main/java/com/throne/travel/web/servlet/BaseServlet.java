package com.throne.travel.web.servlet;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求路径
        String requestURI = req.getRequestURI();
        // 获取请求方法名
        String methodName = requestURI.substring(requestURI.lastIndexOf("/") + 1);
        // 通过反射机制调用对应方法
        try {
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    public void writeJsonToStream(HttpServletResponse resp, Object o) throws IOException {
        // 将对象转为Json并输出到字节流给client
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), o);

    }
    public String writeJsonToString(Object o) throws JsonProcessingException {
        // 将对象输出成Json字符串
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(o);

    }
}
