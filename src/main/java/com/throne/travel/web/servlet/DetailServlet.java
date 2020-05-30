package com.throne.travel.web.servlet;

import com.throne.travel.domain.Route;
import com.throne.travel.domain.RouteImg;
import com.throne.travel.service.ImgService;
import com.throne.travel.service.RouteService;
import com.throne.travel.service.impl.ImgServiceImpl;
import com.throne.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/detail/*")
public class DetailServlet extends BaseServlet {
    private RouteService routeService = new RouteServiceImpl();

    private ImgService imgService = new ImgServiceImpl();

    public void getRouteDetail(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String rid = req.getParameter("rid");
        if (rid==null || rid.length()<=0){
            return;
        }
        Route route  =  routeService.findRouteByid(rid);
        List<RouteImg> routeImgs = imgService.findImgById(rid);

        route.setRouteImgList(routeImgs);
        writeJsonToStream(resp, route);


    }
}
