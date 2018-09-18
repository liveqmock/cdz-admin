package com.ga.cdz.constant;

/**
 * @author:luqi
 * @description: redis key 常量类，变量同一大写，且语义化 final static 修饰
 * @date:2018/9/3_14:25
 */
public class RedisConstant {
    //客户端前缀
    public final static String API_ROOT = "CDZ-API:";

    //管理后台前缀
    public final static String ADMIN_ROOT = "CDZ-ADMIN:";

    //表缓存前缀
    public final static String TABLE_ROOT = "CDZ-TABLE:";

    //列表缓存前缀
    public final static String LIST_ROOT = "CDZ-LIST:";

    //用户token
    public final static String USER_TOKEN = API_ROOT + "USER-TOKEN:";

    //用户注册发送短信
    public final static String USER_REGISTER_SMS = API_ROOT + "USER-REGISTER-SMS:";

    //用户找回密码发送短信
    public final static String USER_RETRIEVE_SMS = API_ROOT + "USER_RETRIEVER_SMS:";

    //用户重置手机号发送短信
    public final static String USER_UPDATE_TEL_SMS = API_ROOT + "USER_UPDATE_TEL_SMS:";

    //管理员token
    public final static String ADMIN_TOKEN = ADMIN_ROOT + "ADMIN-TOKEN:";

    //管理员权限
    public final static String ADMIN_PERM = ADMIN_ROOT + "ADMIN-PERM:";

    //table缓存t_charging_station
    public final static String TABLE_CHARGING_STATION = TABLE_ROOT + "CHARGING-STATION";

    //table缓存t_charging_attach
    public final static String TABLE_CHARGING_ATTACH = TABLE_ROOT + "CHARGING-ATTACH";

    //table缓存t_charging_type
    public final static String TABLE_CHARGING_TYPE = TABLE_ROOT + "CHARGING-TYPE";

    //table缓存t_charging_price
    public final static String TABLE_CHARGING_PRICE = TABLE_ROOT + "CHARGING-PRICE";

    //table缓存t_charging_device
    public final static String TABLE_CHARGING_DEVICE = TABLE_ROOT + "CHARGING-DEVICE";

    //table缓存t_charging_device 按照站点的形式
    public final static String TABLE_CHARGING_DEVICE_STATION = TABLE_ROOT + "CHARGING-DEVICE-STATION";

    //table缓存t_charging_device_sub
    public final static String TABLE_CHARGING_DEVICE_SUB = TABLE_ROOT + "CHARGING-DEVICE-SUB";

    //table缓存t_charging_station_type
    public final static String TABLE_CHARGING_STATION_TYPE = TABLE_ROOT + "CHARGING-STATION-TYPE";

    //table缓存t_charging_shop
    public final static String TABLE_CHARGING_SHOP = TABLE_ROOT + "CHARGING-SHOP";

    //table缓存t_district
    public final static String TABLE_DISTRICT = TABLE_ROOT + "DISTRICT";

    //list缓存站点评分
    public final static String LIST_CHARGING_STATION_SCORE = LIST_ROOT + "CHARGING-STATION-SCORE";

    //list缓存城市的站点列表
    public final static String LIST_CITY_STATION = LIST_ROOT + "CITY:";

    //list缓存首页分页根据设备
    public final static String LIST_MAIN_DEVICE = LIST_ROOT + "MAIN-DEVICE:";

}
