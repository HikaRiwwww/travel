package com.throne.travel.web.servlet;


import com.throne.travel.domain.PageBean;
import com.throne.travel.domain.Route;
import com.throne.travel.service.RouteService;
import com.throne.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    private RouteService service = new RouteServiceImpl();

    public void showRoute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 根据线路类型和当前页码显示对应的线路数据
        String cidStr = req.getParameter("cid");
        String currentPageStr = req.getParameter("currentPage");
        String pageSizeStr = req.getParameter("pageSize");
        String routeName = req.getParameter("keywords");
        if (routeName!=null){
            routeName = new String(routeName.getBytes("iso-8859-1"), "utf-8");

        }
        int cid = 0;
        if (cidStr != null && cidStr.length() >0){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 1;
        if (currentPageStr != null && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        int pageSize = 8;
        if (pageSizeStr != null && pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Route> pb =  service.findRoutesByPage(cid, currentPage, pageSize, routeName);

        writeJsonToStream(resp, pb);
    }

}
