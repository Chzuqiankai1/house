package com.house.service.Impl;

import com.house.dao.AccessibilityDao;
import com.house.model.MedicalDataBean;
import com.house.service.AccessibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessibilityServiceImpl implements AccessibilityService {
    @Autowired
    private AccessibilityDao accessibilityDao;

    @Override
    public List<String> schoolName() {
        return accessibilityDao.schoolName();
    }

    @Override
    public List<String> districtName() {
        return accessibilityDao.districtName();
    }

    @Override
    public double distance(String name) {
        return accessibilityDao.distance(name);
    }

    @Override
    public Integer peopleNum(String name) {
        return accessibilityDao.peopleNum(name);
    }

    @Override
    public Integer teacherNum(String name) {
        return accessibilityDao.teacherNum(name);
    }

    @Override
    public Integer schoolLevel(String name) {
        return accessibilityDao.schoolLevel(name);
    }

    @Override
    public float getCoordinatesX(String name) {
        return accessibilityDao.getCoordinatesX(name);
    }

    @Override
    public float getCoordinatesY(String name) {
        return accessibilityDao.getCoordinatesY(name);
    }

    @Override
    public List<MedicalDataBean> chzuServiceData(float pointx, float pointy, String tableName) {
        return accessibilityDao.chzuServiceData(pointx,pointy,tableName);
    }

    @Override
    public String getChzuServiceDataWKT(String objectid, String tableName) {
        return accessibilityDao.getChzuServiceDataWKT(objectid,tableName);
    }

    @Override
    public String queryChzuServiceAddr(String objectid, String tableName) {
        return accessibilityDao.queryChzuServiceAddr(objectid, tableName);
    }

    @Override
    public String queryChzuServiceStreet(String objectid, String tableName) {
        return accessibilityDao.queryChzuServiceStreet(objectid, tableName);
    }

}
