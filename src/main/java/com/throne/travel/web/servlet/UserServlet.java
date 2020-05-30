package com.throne.travel.web.servlet;

import com.throne.travel.domain.ResultInfo;
import com.throne.travel.domain.User;
import com.throne.travel.service.UserService;
import com.throne.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService service = new UserServiceImpl();

    public void activateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String code = req.getParameter("code");
        if (!(username == null || "".equals(username) || code == null || "".equals(code))) {
            boolean flag = service.verifyUserActivateCode(username, code);
            if (flag) {
                service.updateActivateStatus(username, "Y");
                resp.sendRedirect(req.getContextPath() + "/acvtivate_succeed.html");
            } else {
                resp.sendRedirect(req.getContextPath() + "/activate_failed.html");
            }

        }
    }

    public void currentUser(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        writeJsonToStream(resp, user);
    }

    public void userLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String checkCode = req.getParameter("check");
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        ResultInfo info = new ResultInfo();
        if (!checkCode.equalsIgnoreCase(checkcode_server)) {
            info.setFlag(false);
            info.setErrorMsg("验证码错误，请重新输入");
        } else {
            Map<String, String[]> parameterMap = req.getParameterMap();
            User u = new User();
            try {
                BeanUtils.populate(u, parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            User loginedUser = service.login(u);
            if (loginedUser != null) {
                String status = loginedUser.getStatus();
                if ("Y".equalsIgnoreCase(status)) {
                    info.setFlag(true);
                    session.setAttribute("user", loginedUser);
                } else {
                    info.setFlag(false);
                    info.setErrorMsg("用户尚未激活");
                }
            } else {
                info.setFlag(false);
                info.setErrorMsg("登录失败，用户名或密码错误");

            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        objectMapper.writeValue(resp.getOutputStream(), info);
    }

    public void userLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        session.invalidate();
        resp.sendRedirect(req.getContextPath() + "/index.html");
    }

    public void userRegister(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String check = req.getParameter("check");
        System.out.println(req.getParameter("sex"));
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if (!check.equalsIgnoreCase(checkcode_server)) {
            return;
        }
        Map<String, String[]> parameterMap = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, parameterMap);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        boolean flag = service.register(user);
        ResultInfo info = new ResultInfo(flag);
        if (!flag) {
            info.setErrorMsg("注册失败");
        }

        writeJsonToStream(resp,info);
    }
}
