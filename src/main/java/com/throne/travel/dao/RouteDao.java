package com.throne.travel.dao;

import com.throne.travel.domain.Route;

import java.util.List;

public interface RouteDao {

    List<Route> findRoutesByPage(int cid, int start, int pageSize, String routeName);

    int findTotalCount(int cid, String routeName);

    Route findRouteByid(String rid);
}
