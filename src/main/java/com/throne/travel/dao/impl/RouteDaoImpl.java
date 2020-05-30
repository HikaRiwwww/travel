package com.throne.travel.dao.impl;

import com.throne.travel.dao.RouteDao;
import com.throne.travel.domain.Route;
import com.throne.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public Route findRouteByid(String rid) {
        String sql = "SELECT * FROM tab_route where rid = ?";
        Route route = null;
        try{
            route = template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
        }catch (Exception ignored){}
        return route;

    }

    @Override
    public List<Route> findRoutesByPage(int cid, int start, int pageSize, String routeName) {
//        String sql = "SELECT * FROM tab_route WHERE cid = ? limit ?, ?";
        String sql = "SELECT * FROM tab_route WHERE 1=1 ";
        StringBuilder sqlBuilder = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid!=0){
            sqlBuilder.append("and cid = ? ");
            params.add(cid);
        }
        if (routeName!=null && routeName.length()>0){
            sqlBuilder.append("and rname like ? ");
            params.add("%" + routeName + "%");
        }
        sqlBuilder.append(" limit ?, ?");
        sql = sqlBuilder.toString();
        System.out.println("sql:" + sql);
        params.add(start);
        params.add(pageSize);
        System.out.println(params.toString());
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
    }

    @Override
    public int findTotalCount(int cid, String routeName) {
//        String sql = "SELECT COUNT(*) FROM tab_route WHERE cid = ?";
        String sql = "SELECT COUNT(*) FROM tab_route WHERE 1=1 ";
        StringBuilder sqlBuilder = new StringBuilder(sql);
        List params = new ArrayList();
        if (cid!=0){
            sqlBuilder.append("and cid = ? ");
            params.add(cid);
        }
        if (routeName!=null && routeName.length()>0){
            sqlBuilder.append("and rname like ? ");
            params.add("%" + routeName + "%");
        }

        sql = sqlBuilder.toString();
        System.out.println("sql:" + sql);
        System.out.println(params.toString());
        return template.queryForObject(sql, Integer.class, params.toArray());

    }
}
