package com.ga.cdz.controller.demo;

import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.bean.ValidException;
import com.ga.cdz.domain.vo.admin.FileDemoVo;
import com.ga.cdz.util.MFileUtil;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * @author:luqi
 * @description: 文件上传测试控制层
 * @date:2018/9/10_10:18
 */
@Slf4j
@RestController
@RequestMapping("/demo/file")
public class FileDemoController {

    /**
     * 文件工具类
     */
    @Resource
    MFileUtil mFileUtil;

    /**
     * 用户头像路径
     */
    @Value("${file.user_avatar}")
    protected String userAvatarFilePath;

    /**
     * @author:luqi
     * @description: 上传文件 不带参数
     * @date:2018/9/10_10:20
     * @param: file对象
     * @return: result
     */
    @PostMapping("/easy/upload")
    public Result fileUpload(@RequestParam("file") MultipartFile file) {
        if (ObjectUtils.isEmpty(file)) {
            throw new BusinessException("上传文件为空");
        }
        String fileName = file.getOriginalFilename();
        /**判断是不是文件名*/
        boolean isImg = mFileUtil.isImgFileName(fileName);
        if (!isImg) {
            throw new BusinessException("不是图片文件");
        }
        /**生成新的文件名*/
        String newFileName = mFileUtil.renameFile(fileName);
        /**生成新的文件目录,假设存储用户id为1的用户头像*/
        String newFilePath = mFileUtil.getTimePath() + File.separator + "1" + File.separator;
        /**保存在数据库的dbFilePath*/
        String dbFilePath = newFilePath + newFileName;
        /**保存文件*/
        String filePathAndName = userAvatarFilePath + dbFilePath;
        boolean isSuccess = mFileUtil.saveFile(file, filePathAndName);
        if (!isSuccess) {
            throw new BusinessException("文件上传失败");
        }
        log.info("dbFilePath===>{}", dbFilePath);
        /***
         * 这里做一些service层面的操作
         *
         * */
        return Result.success().message("文件上传成功");
    }


    /**
     * @author:luqi
     * @description: 单文件上传带 参数
     * @date:2018/9/10_11:23
     * @param: file对象
     * @param: FileDemoVo 对象 (注意这里不能使用 @RequestBody标签和@Validated标签，
     * 参数的验证需要手动判断)
     * @return: Result
     */
    @PostMapping("/object/upload")
    public Result fileUploadObjcet(@RequestParam("file") MultipartFile file, FileDemoVo vo) {
        if (ObjectUtils.isEmpty(file)) {
            throw new BusinessException("上传文件为空");
        }
        Integer userId = vo.getUserId();
        if (ObjectUtils.isEmpty(userId)) {
            throw new ValidException("userId不为空");
        }
        String fileName = file.getOriginalFilename();
        /**判断是不是文件名*/
        boolean isImg = mFileUtil.isImgFileName(fileName);
        if (!isImg) {
            throw new BusinessException("不是图片文件");
        }
        /**生成新的文件名*/
        String newFileName = mFileUtil.renameFile(fileName);
        /**生成新的文件目录*/
        String newFilePath = mFileUtil.getTimePath() + File.separator + userId + File.separator;
        /**保存在数据库的dbFilePath*/
        String dbFilePath = newFilePath + newFileName;
        /**保存文件*/
        String filePathAndName = userAvatarFilePath + dbFilePath;
        boolean isSuccess = mFileUtil.saveFile(file, filePathAndName);
        if (!isSuccess) {
            throw new BusinessException("文件上传失败");
        }
        log.info("dbFilePath===>{}", dbFilePath);
        /***
         * 这里做一些service层面的操作
         *
         * */
        return Result.success().message("文件上传成功");
    }


    /**
     * @author:luqi
     * @description: 多文件上传带参数
     * @date:2018/9/10_11:57
     * @param: file数据对象
     * @return:
     */
    @PostMapping("/mul/upload")
    public Result fileUploadMul(@RequestParam("files") MultipartFile[] multipartFiles, FileDemoVo vo) {
        if (ObjectUtils.isEmpty(multipartFiles) || multipartFiles.length == 0) {
            throw new BusinessException("上传文件为空");
        }
        /**
         * 验证参数
         * */
        Integer userId = vo.getUserId();
        if (ObjectUtils.isEmpty(userId)) {
            throw new ValidException("userId不为空");
        }
        /**上传图片成功的计数*/
        int successCount = 0;
        /**最后保存到数据库的文件路径集合*/
        List<String> dbFilePaths = Lists.newArrayList();
        for (MultipartFile fileItem : multipartFiles) {
            String fileName = fileItem.getOriginalFilename();
            /**判断是不是文件名*/
            boolean isImg = mFileUtil.isImgFileName(fileName);
            if (!isImg) {
                throw new BusinessException("不是图片文件");
            }
            /**生成新的文件名*/
            String newFileName = mFileUtil.renameFile(fileName);
            /**生成新的文件目录*/
            String newFilePath = mFileUtil.getTimePath() + File.separator + userId + File.separator;
            /**保存在数据库的dbFilePath*/
            String dbFilePath = newFilePath + newFileName;
            /**保存文件*/
            String filePathAndName = userAvatarFilePath + dbFilePath;
            boolean isSuccess = mFileUtil.saveFile(fileItem, filePathAndName);
            if (!isSuccess) {
                throw new BusinessException("文件上传失败");
            }
            dbFilePaths.add(dbFilePath);
            successCount++;
        }
        /**判断文件是否全部上传成功*/
        if (successCount != multipartFiles.length) {
            throw new BusinessException("上传文件失败");
        }
        /***字符串我们全部用分号拼接,且不要最后一份分号*/
        String dbFilePathStr = String.join(";", dbFilePaths);
        dbFilePathStr = dbFilePathStr.substring(0, dbFilePathStr.length() - 1);
        log.info("dbFilePathStr,{}", dbFilePathStr);
        /***
         * 做一些逻辑操作
         * */
        return Result.success().message("上传文件成功");

    }

}
