package com.throne.travel.service;

import com.throne.travel.domain.RouteImg;

import java.util.List;

public interface ImgService {
    public List<RouteImg> findImgById(String rid);
}
