package com.house.dao;

import com.house.model.MedicalDataBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccessibilityDao {
    List<String> schoolName();

    List<String> districtName();

    double distance(String name);

    Integer peopleNum(String name);

    Integer teacherNum(String name);

    Integer schoolLevel(String name);

    float getCoordinatesX(String name);

    float getCoordinatesY(String name);

    List<MedicalDataBean> chzuServiceData(@Param("pointx") float pointx,@Param("pointy") float pointy,@Param("tableName") String tableName);

    String getChzuServiceDataWKT(@Param("objectid") String objectid,@Param("tableName") String tableName);

    String queryChzuServiceAddr(@Param("objectid") String objectid,@Param("tableName") String tableName);

    String queryChzuServiceStreet(@Param("objectid") String objectid,@Param("tableName") String tableName);

}
