package com.house.web.util;

import com.house.model.MedicalDataBean;
import com.house.service.AccessibilityService;
import com.house.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/accessibility")
@CrossOrigin
public class AccessibilityController {
    @Autowired
    private AccessibilityService accessibilityService;

    @ResponseBody
    @RequestMapping(value = "/accessibilitySchool",method = RequestMethod.GET)
    public Map<String,Object> accessibilitySchool(HttpServletRequest request){
        String districtName = HttpServletRequestUtil.getString(request,"name");
        Map<String,Object> modelMap = new HashMap<>();

        List<String> schoolNameList = accessibilityService.schoolName();
        double level = 0;
        double data = 0;
        for (int i = 0;i<schoolNameList.size();i++){
            String schoolName = schoolNameList.get(i);
            double v = getV(schoolName);

            int teacherNum = accessibilityService.teacherNum(schoolName);
            int schoolLevel = accessibilityService.schoolLevel(schoolName);
            double s = (teacherNum + schoolLevel) * 0.5;
            String distanceName = districtName+ " - " + schoolName;
            double singleDistance = accessibilityService.distance(distanceName);
            double distanceLevel = Math.pow(singleDistance,-1);

            level = s * distanceLevel / v + level;
            DecimalFormat df = new DecimalFormat("0.0000");
            data = Double.parseDouble(df.format(level)) * 10;
        }
        modelMap.put("data",data);
        return modelMap;
    }

    public double getV(String schoolName){
        List<String> districtNameList = accessibilityService.districtName();
        double v = 0;

        for (int j = 0;j<districtNameList.size();j++){
            String districtName = districtNameList.get(j);
            String distanceName = districtName+ " - " + schoolName;

            double distance = accessibilityService.distance(distanceName);
            double distanceLevel = Math.pow(distance,-1);
            int peopleNum = accessibilityService.peopleNum(districtName);
            v =  distanceLevel * peopleNum + v;
        }
        return v;
    }

    /**
     * 查询小区附近附属设施
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryChzuServiceData",method = RequestMethod.GET)
    public Map<String,Object> queryChzuServiceData(HttpServletRequest request){
        String name = HttpServletRequestUtil.getString(request,"name");
        String tableName = HttpServletRequestUtil.getString(request,"tableName");

        Map<String,Object> modelMap = new HashMap<>();
        Map<String,Object> dataMap = new HashMap<>();

        List modelList = new ArrayList();
        List lengthList = new ArrayList();

        try {
            float x = accessibilityService.getCoordinatesX(name);
            float y = accessibilityService.getCoordinatesY(name);

            List<MedicalDataBean> medicalServiceData = accessibilityService.chzuServiceData(x,y,tableName);
            for (int i = 0;i< medicalServiceData.size();i++){
                MedicalDataBean medicalData = medicalServiceData.get(i);
                String objectid = medicalData.getObjectId();
                String serviceName = medicalData.getName();
                String distance = medicalData.getDist();
                String addr = accessibilityService.queryChzuServiceAddr(objectid,tableName);
                String street = accessibilityService.queryChzuServiceStreet(objectid,tableName);
                String serviceWKT = accessibilityService.getChzuServiceDataWKT(objectid,tableName);

                Map<String,Object> attributeMap = new HashMap<>();

                if(distance != "0"){
                    modelMap.put("name",serviceName);
                    modelMap.put("Shape",serviceWKT);
                    attributeMap.put("地址：",addr);
                    attributeMap.put("所属街道：",street);
                    attributeMap.put("最短距离：",distance);
                    modelMap.put("attribute",attributeMap);
                    lengthList.add(1);
                    modelList.add(modelMap);
                    modelMap = new HashMap<>();
                }
            }
            dataMap.put("data",modelList);
            dataMap.put("length",lengthList.size());
            dataMap.put("status",1);
        }catch (Exception e){
            dataMap.put("message",e.getMessage());
            dataMap.put("status",0);
        }
        return dataMap;
    }
}
