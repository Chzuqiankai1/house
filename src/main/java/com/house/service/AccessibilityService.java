package com.house.service;

import com.house.model.MedicalDataBean;

import java.util.List;

public interface AccessibilityService {
    List<String> schoolName();

    List<String> districtName();

    double distance(String name);

    Integer peopleNum(String name);

    Integer teacherNum(String name);

    Integer schoolLevel(String name);

    float getCoordinatesX(String name);

    float getCoordinatesY(String name);

    List<MedicalDataBean> chzuServiceData(float pointx,float pointy,String tableName);

    String getChzuServiceDataWKT(String objectid,String tableName);

    String queryChzuServiceAddr(String objectid,String tableName);

    String queryChzuServiceStreet(String objectid, String tableName);
}
