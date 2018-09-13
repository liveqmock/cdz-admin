package com.ga.cdz.util;

import org.springframework.stereotype.Component;

/**
 * @author:luqi
 * @description: 根据两个位置的经纬度，来计算两地的距离（单位为KM）
 * @date:2018/9/13_15:38
 */
@Component("mDistanceUtil")
public class MDistanceUtil {

    private final double EARTH_RADIUS = 6378.137;

    private double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两个位置的经纬度，来计算两地的距离
     *
     * @param longitudeUser 用户位置经度
     * @param latitudeUser  用户纬度
     * @param longitudeStation 站点经度
     * @param latitudeStation  站点纬度
     * @return 返回距离单位为千米
     */
    public double getDistance(double longitudeUser, double latitudeUser, double longitudeStation, double latitudeStation) {
        double a, b, d, sa2, sb2;
        latitudeUser = rad(latitudeUser);
        latitudeStation = rad(latitudeStation);
        a = latitudeUser - latitudeStation;
        b = rad(longitudeUser - longitudeStation);
        sa2 = Math.sin(a / 2.0);
        sb2 = Math.sin(b / 2.0);
        d = 2 * EARTH_RADIUS
                * Math.asin(Math.sqrt(sa2 * sa2 + Math.cos(latitudeUser)
                * Math.cos(latitudeStation) * sb2 * sb2));
        return d;
    }

}
