package com.throne.travel.dao;

import com.throne.travel.domain.RouteImg;

import java.util.List;

public interface ImgDao {
    public List<RouteImg> findImgById(String rid);
}
