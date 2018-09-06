package com.ga.cdz.constant;
/**
 * @author:luqi
 * @description: redis key 常量类，变量同一大写，且语义化 final static 修饰
 * @date:2018/9/3_14:25
 */
public class RedisConstant {
        //客户端前缀
        public final static String API_ROOT="CDZ-API:";

        //管理后台前缀
        public final static String ADMIN_ROOT="CDZ-ADMIN:";


    //用户标识
        public final static String USER=API_ROOT+"USER:";

    //管理员token
    public final static String ADMIN_TOKEN = ADMIN_ROOT + "ADMIN-TOKEN:";

    //管理员权限
    public final static String ADMIN_PERM = ADMIN_ROOT + "ADMIN-PERM:";

}
