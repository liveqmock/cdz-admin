package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.UserInfoMapper;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.dto.api.MyInfoDTO;
import com.ga.cdz.domain.entity.UserInfo;
import com.ga.cdz.domain.vo.api.MyInfoVo;
import com.ga.cdz.service.IUserService;
import com.ga.cdz.util.MFileUtil;
import com.ga.cdz.util.MUtil;
import com.vdurmont.emoji.EmojiParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;


/**
 * @author:luqi
 * @description: 用户接口具体实现类
 * @date:2018/9/4_11:02
 */
@Slf4j
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserService {

    /**
     * 用户头像存储路径
     */
    @Value("${file.user_avatar}")
    protected String userAvatarFilePath;

    @Value("${url.user_avatar}")
    protected String userAvatarUrl;

    /**
     * 文件工具类
     */
    @Resource
    MFileUtil mFileUtil;

    @Resource
    MUtil mUtil;

    @Override
    public MyInfoDTO getMyInfoDTOById(Integer id) {
        MyInfoDTO myInfoDTO = baseMapper.getMyInfoDTOById(id);
        String userAvatar = userAvatarFilePath + myInfoDTO.getUserAvatar();
        myInfoDTO.setUserAvatar(userAvatar);
        if (ObjectUtils.isEmpty(myInfoDTO.getUserPrice())) {
            myInfoDTO.setUserPrice(new BigDecimal("0"));
        }
        if (ObjectUtils.isEmpty(myInfoDTO.getCoupon())) {
            myInfoDTO.setCoupon(0);
        }
        if (ObjectUtils.isEmpty(myInfoDTO.getCarbon())) {
            myInfoDTO.setCarbon(new BigDecimal(0));
        }
        return myInfoDTO;
    }

    @Override
    public boolean isUserFreeze(String tel) {
        UserInfo hasUserInfo = baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda()
                .eq(UserInfo::getUserTel, tel));
        if (!ObjectUtils.isEmpty(hasUserInfo)) {
            if (hasUserInfo.getUserState().equals(UserInfo.UserState.FREEZE)) {
                //被冻结了
                return true;
            }
        }
        //没有冻结
        return false;
    }

    @Override
    public UserInfo uploadAvatar(MultipartFile file, Integer userId) {
        UserInfo userInfo = baseMapper.selectById(userId);
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new BusinessException("用户不存在");
        }
        String fileName = file.getOriginalFilename();
        boolean isImg = mFileUtil.isImgFileName(fileName);
        if (!isImg) {
            throw new BusinessException("不是图片文件");
        }
        boolean isOutImgSize = mFileUtil.isImgOutFileSize(file);
        if (isOutImgSize) {
            throw new BusinessException("图片不能大于2M");
        }
        /**生成新的文件名*/
        String newFileName = mFileUtil.renameFile(fileName);
        /**生成新的文件目录,假设存储用户id为1的用户头像*/
        String newFilePath = mFileUtil.getTimePath() + File.separator + userId + File.separator;
        /**保存在数据库的dbFilePath*/
        String dbFilePath = newFilePath + newFileName;
        /**保存文件*/
        String filePathAndName = userAvatarFilePath + dbFilePath;
        boolean isSuccess = mFileUtil.saveFile(file, filePathAndName);
        if (!isSuccess) {
            throw new BusinessException("文件上传失败");
        }
        /**数据库更新**/
        UserInfo updateAvatarUserInfo = new UserInfo();
        updateAvatarUserInfo.setUserId(userId).setUserAvatar(dbFilePath);
        int row = baseMapper.updateById(updateAvatarUserInfo);
        if (row == 0) {
            throw new BusinessException("头像上传失败，稍后重试");
        }
        /**新的头像路径*/
        String backUserAvatarUrl = userAvatarUrl + dbFilePath;
        UserInfo backUserInfo = new UserInfo();
        backUserInfo.setUserAvatar(backUserAvatarUrl);
        return backUserInfo;
    }

    @Override
    public void updateTel(MyInfoVo myInfoVo) {
        String userTel = myInfoVo.getUserTel();
        Integer userId = myInfoVo.getUserId();
        //判断tel是否存在
        UserInfo dbUserInfo = baseMapper.selectOne(new QueryWrapper<UserInfo>().lambda().eq(UserInfo::getUserTel, userTel));
        if (!ObjectUtils.isEmpty(dbUserInfo) && !dbUserInfo.getUserId().equals(userId)) {
            throw new BusinessException("电话号码已注册");
        }
        UserInfo updateUserInfo = new UserInfo().setUserTel(userTel).setUserId(userId);
        int row = baseMapper.updateById(updateUserInfo);
        if (row == 0) {
            throw new BusinessException("操作失败，稍后重试");
        }
    }

    @Override
    public void updateRealName(MyInfoVo myInfoVo) {
        String userRealName = myInfoVo.getUserRealName();
        Integer userId = myInfoVo.getUserId();
        /**表情过滤**/
        userRealName = EmojiParser.removeAllEmojis(userRealName);
        if (StringUtils.isEmpty(userRealName)) {
            throw new BusinessException("不能输入全表情字段");
        }
        UserInfo updateUserInfo = new UserInfo().setUserId(userId).setUserRealName(userRealName);
        int row = baseMapper.updateById(updateUserInfo);
        if (row == 0) {
            throw new BusinessException("操作失败，稍后重试");
        }
    }

    @Override
    public void updateNickName(MyInfoVo myInfoVo) {
        String userNickName = myInfoVo.getUserNickName();
        Integer userId = myInfoVo.getUserId();
        /**表情过滤**/
        userNickName = EmojiParser.removeAllEmojis(userNickName);
        if (StringUtils.isEmpty(userNickName)) {
            throw new BusinessException("不能输入全表情字段");
        }
        UserInfo updateUserInfo = new UserInfo().setUserId(userId).setUserNickName(userNickName);
        int row = baseMapper.updateById(updateUserInfo);
        if (row == 0) {
            throw new BusinessException("操作失败，稍后重试");
        }
    }

    @Override
    public void updatePwd(MyInfoVo myInfoVo) {
        String userPwd = myInfoVo.getUserPwd();
        Integer userId = myInfoVo.getUserId();
        /**md5*/
        String md5Pwd = mUtil.MD5(userPwd);
        UserInfo updateUserInfo = new UserInfo().setUserId(userId).setUserPwd(md5Pwd);
        int row = baseMapper.updateById(updateUserInfo);
        if (row == 0) {
            throw new BusinessException("操作失败，稍后重试");
        }
    }
}
