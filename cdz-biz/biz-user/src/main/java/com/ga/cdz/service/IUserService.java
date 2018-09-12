package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.api.MyInfoDTO;
import com.ga.cdz.domain.entity.UserInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author:luqi
 * @description: 用户接口
 * @date:2018/9/4_10:59
 */
public interface IUserService extends IService<UserInfo> {

     /**
      * @author:luqi
      * @description: 获取我的信息
      * @date:2018/9/4_10:59
      * @param: id
      * @return: User实体类
      */
     MyInfoDTO getMyInfoDTOById(Integer id);

    /**
     * @author:luqi
     * @description: 判断用户是否冻结
     * @date:2018/9/11_16:54
     * @param: 用户电话
     * @return: 是否
     */
    boolean isUserFreeze(String tel);

    /**
     * @author:luqi
     * @description: 上传头像
     * @date:2018/9/12_10:06
     * @param:
     * @return:
     */
    void uploadAvatar(MultipartFile file, Integer userId);

}
