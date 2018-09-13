package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.api.MyInfoDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.api.MyInfoVo;
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
    UserInfo uploadAvatar(MultipartFile file, Integer userId);


    /**
     * @author:luqi
     * @description: 更新电话，发送验证码
     * @date:2018/9/13_9:26
     * @param:
     * @return:
     */
    void uploadTelSms(MyInfoVo myInfoVo);

    /**
     * @author:luqi
     * @description: 更新电话
     * @date:2018/9/12_14:15
     * @param:
     * @return:
     */
    void updateTel(MyInfoVo myInfoVo);


    /**
     * @author:luqi
     * @description: 更新真实姓名
     * @date:2018/9/12_14:15
     * @param:
     * @return:
     */
    void updateRealName(MyInfoVo myInfoVo);

    /**
     * @author:luqi
     * @description: 更新昵称
     * @date:2018/9/12_14:15
     * @param:
     * @return:
     */
    void updateNickName(MyInfoVo myInfoVo);


    /**
     * @author:luqi
     * @description: 密码更新
     * @date:2018/9/12_14:32
     * @param:
     * @return:
     */
    void updatePwd(MyInfoVo myInfoVo);


}
