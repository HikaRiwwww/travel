package com.throne.travel.service.impl;

import com.throne.travel.dao.ImgDao;
import com.throne.travel.dao.impl.ImgDaoImpl;
import com.throne.travel.domain.RouteImg;
import com.throne.travel.service.ImgService;

import java.util.List;

public class ImgServiceImpl implements ImgService {
    ImgDao imgDao = new ImgDaoImpl();

    @Override
    public List<RouteImg> findImgById(String rid) {
        return imgDao.findImgById(rid);
    }
}
