package com.throne.travel.service.impl;

import com.throne.travel.dao.RouteDao;
import com.throne.travel.dao.impl.RouteDaoImpl;
import com.throne.travel.domain.PageBean;
import com.throne.travel.domain.Route;
import com.throne.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteDao dao = new RouteDaoImpl();

    @Override
    public PageBean<Route> findRoutesByPage(int cid, int currentPage, int pageSize, String routeName) {
        PageBean<Route> pb = new PageBean<Route>();

        pb.setCurrentPage(currentPage);
        pb.setPageSize(pageSize);

        int totalCount = dao.findTotalCount(cid, routeName);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Route> routes = dao.findRoutesByPage(cid, start, pageSize, routeName);
        pb.setList(routes);

        int totalPage = totalCount % pageSize == 0? (totalCount / pageSize) :(totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findRouteByid(String rid) {
        return dao.findRouteByid(rid);
    }
}

