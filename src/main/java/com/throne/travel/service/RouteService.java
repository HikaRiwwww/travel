package com.throne.travel.service;

import com.throne.travel.domain.PageBean;
import com.throne.travel.domain.Route;

public interface RouteService {
    PageBean<Route> findRoutesByPage(int cid, int currentPage, int pageSize, String routeName);

    Route findRouteByid(String rid);
}
