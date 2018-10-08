package com.ga.cdz.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.UserSms;
import com.ga.cdz.domain.group.admin.IMUserSmsGroup;
import com.ga.cdz.domain.vo.admin.UserSmsAddVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMUserSmsService;
import com.ga.cdz.util.MFileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 消息发布控制层
 * @date:2018/9/12 14:27
 */
@RestController
@RequestMapping("/user/sms")
public class UserSmsController extends AbstractBaseController {
    /**
     * 文件工具类
     */
    @Resource
    MFileUtil mFileUtil;
    /**
     * 广告图片URL路径
     */
    @Value("${url.banner}")
    protected String banner;
    /**
     * 系统消息图片URL路径
     */
    @Value("${url.system}")
    protected String system;
    /**
     * 系统图片文件路径
     */
    @Value("${file.system}")
    protected String systemFile;
    /**
     * 自定义html文件路径
     */
    @Value("${file.custom_url}")
    protected String customFile;
    /**
     * 自定义html访问路径
     */
    @Value("${url.custom_url}")
    protected String customUrl;
    /**
     * 广告图片文件路径
     */
    @Value("${file.banner}")
    protected String bannerFile;
    /**
     * 消息服务
     */
    @Resource
    IMUserSmsService mUserSmsService;

    /**
     * @author:wanzhongsu
     * @description: 根据消息ID删除消息
     * @date: 2018/9/12 18:22
     * @param: UserSmsAddVo
     * @return: Result
     */
    @PostMapping("/delete")
    public Result deleteSmsById(@RequestBody @Validated(value = IMUserSmsGroup.Delete.class) UserSmsAddVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        try {
            mUserSmsService.removeById(vo.getSmsId());
            return Result.success().message("删除成功");
        } catch (Exception e) {
            throw new BusinessException("删除失败");
        }
    }

    /**
     * @author:wanzhongsu
     * @description: 发布消息，创建文件
     * @date: 2018/10/8 15:05
     * @param: UserSmsAddVo
     * @return: Result
     */
    @PostMapping("/publish")
    @Transactional
    public Result publisSms(@RequestBody @Validated(value = IMUserSmsGroup.Add.class) UserSmsAddVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        System.out.println(vo.getSmsUrl());
        String content = vo.getSmsUrl();
        vo.setSmsUrl("等待更新......");
        int smsId = mUserSmsService.saveSms(vo);

        /**生成新的文件目录*/
        String newFilePath = mFileUtil.getTimePath() + smsId;
        /**保存在数据库的dbFilePath*/
        String dbFilePath = newFilePath;
        newFilePath = customFile + newFilePath;
        newFilePath = newFilePath + ".html";
        File file = new File(newFilePath);

        dbFilePath = customUrl + dbFilePath + ".html";
        //保存文件
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(content.getBytes());
            outputStream.flush();
            //更新数据库中的文件
            dbFilePath = dbFilePath.replaceAll("\\\\", "/");
            vo.setSmsUrl(dbFilePath);
            UserSms userSms = new UserSms();
            BeanUtils.copyProperties(vo, userSms);
            userSms.setSmsId(smsId);
            mUserSmsService.updateById(userSms);
            return Result.success().message("提交成功");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("提交失败");
        }
    }

    /**
     * @author:wanzhongsu
     * @description: 添加消息
     * @date: 2018/9/12 18:06
     * @param: MultipartFile  UserSmsAddVo
     * @return: Result
     */
    @PostMapping("/add")
    public Result saveSms(@RequestParam("file") MultipartFile file, UserSmsAddVo vo) {
        //初步检查文件是否为空
        if (ObjectUtils.isEmpty(file)) {
            throw new BusinessException("上传文件为空");
        }
        if (ObjectUtils.isEmpty(vo)) {
            throw new BusinessException("上传信息为空");
        }
        //根据消息类型确定上传父目录
        String fileParent = null;
        if (vo.getSmsInt() == 1) {
            vo.setSmsType(UserSms.SmsType.SYSTEM);
            fileParent = systemFile;
        } else if (vo.getSmsInt() == 2) {
            fileParent = bannerFile;
            vo.setSmsType(UserSms.SmsType.BANNER);
        } else {
            throw new BusinessException("消息类型错误");
        }
        //保存文件获取id
        int smsId = mUserSmsService.saveSms(vo);
        String fileName = file.getOriginalFilename();
        /**判断是不是文件名*/
        boolean isImg = mFileUtil.isImgFileName(fileName);
        if (!isImg) {
            throw new BusinessException("文件不是图片文件");
        }
        /**生成新的文件名*/
        String newFileName = mFileUtil.renameFile(fileName);
        /**生成新的文件目录*/
        String newFilePath = mFileUtil.getTimePath() + smsId;
        /**保存在数据库的dbFilePath*/
        String dbFilePath = newFilePath + File.separator + newFileName;
        //保存文件
        mFileUtil.saveFile(file, fileParent + File.separator + dbFilePath);
        //更新数据库中文件的路径
        vo.setSmsPic(dbFilePath);
        UserSms userSms = new UserSms();
        BeanUtils.copyProperties(vo, userSms);
        try {
            userSms.setSmsId(smsId);
            mUserSmsService.updateById(userSms);
            return Result.success().message("提交成功");
        } catch (Exception e) {
            throw new BusinessException("提交失败");
        }
    }

    /**
     * @author:wanzhongsu
     * @description: 获取系统消息列表
     * @date: 2018/9/12 16:56
     * @param:
     * @return:
     */
    @PostMapping("/system/list")
    public Result getSystemPage(@RequestBody @Validated PageVo<UserSmsAddVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        if (ObjectUtils.isEmpty(vo.getData())) {
            vo.setData(new UserSmsAddVo());
        }
        //设置获取消息类型为系统消息
        vo.getData().setSmsType(UserSms.SmsType.SYSTEM);
        //获取消息
        IPage<UserSms> page = mUserSmsService.getSmsPage(vo);
        List<UserSms> list = page.getRecords();
        //将消息中的图片路径进行规范化
        list.forEach(item -> {
            String uri = item.getSmsPic();
            uri = system + uri;
            uri = uri.replaceAll("\\\\", "/");
            uri.replaceAll("//", "/").replaceFirst("/", "//");
            item.setSmsPic(uri);
        });
        //返回消息
        return Result.success().data(page);
    }

    /**
     * @author:wanzhongsu
     * @description: 获取广告消息列表
     * @date: 2018/9/12 16:56
     * @param:
     * @return:
     */
    @PostMapping("/banner/list")
    public Result getBannerPage(@RequestBody @Validated PageVo<UserSmsAddVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        //对vo进行初始化，保证传进服务里的对象不为空
        if (ObjectUtils.isEmpty(vo.getData())) {
            vo.setData(new UserSmsAddVo());
        }
        //设置消息类型为banner
        vo.getData().setSmsType(UserSms.SmsType.BANNER);
        //获取banner消息
        IPage<UserSms> page = mUserSmsService.getSmsPage(vo);
        //对获取的消息图片路径进行规范化
        List<UserSms> list = page.getRecords();
        list.forEach(item -> {
            String uri = item.getSmsPic();
            uri = banner + uri;
            uri = uri.replaceAll("\\\\", "/");
            uri.replaceAll("//", "/").replaceFirst("/", "//");
            item.setSmsPic(uri);
            item.setSmsPic(uri);
        });
        //返回banner消息
        return Result.success().data(page);
    }
}

