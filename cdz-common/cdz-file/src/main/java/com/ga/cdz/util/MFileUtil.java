package com.ga.cdz.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author:luqi
 * @description: 文件工具类
 * @date:2018/9/10_9:15
 */
@Component("mFileUtil")
public class MFileUtil {

    /**
     * @author:luqi
     * @description: 保存文件到指定目录
     * @date:2018/9/10_9:30
     * @param: MultipartFile file对象
     * @param: filePathAndName 文件的存储路径（带文件名）
     * @return: 是否成功
     */
    public boolean saveFile(MultipartFile multipartFile, String filePathAndName) {
        File file = new File(filePathAndName);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()) {
            fileParent.mkdirs();
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    /**
     * @author:luqi
     * @description: 获取时间 文件路径 格式 /yyyy/MM/dd
     * @date:2018/9/10_9:55
     * @param:
     * @return: 返回时间路径
     */
    public String getTimePath() {
        Calendar calendar = Calendar.getInstance();
        String year = calendar.get(Calendar.YEAR) + "";
        String month = "";
        int monthInt = calendar.get(Calendar.MONTH) + 1;
        if (monthInt < 10) {
            month += "0";
        }
        month += monthInt;
        String day = "";
        int dayInt = calendar.get(Calendar.DATE);
        if (dayInt < 10) {
            day += "0";
        }
        day += dayInt;
        String sparator = File.separator;
        StringBuffer stringBuffer = new StringBuffer().append(sparator)
                .append(year).append(sparator)
                .append(month).append(sparator)
                .append(day).append(sparator);
        return stringBuffer.toString();
    }

    /**
     * @author:luqi
     * @description: 根据源文件名生成 目标文件名
     * @date:2018/9/10_10:12
     * @param: 源文件名称
     * @return: 返回新的文件名
     */
    public String renameFile(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        UUID newFileName = UUID.randomUUID();
        return newFileName.toString() + suffix;
    }

    /**
     * @author:luqi
     * @description: 检查文件名是否为图片 文件格式目前支持 .jpg/.png/.jpeg
     * @date:2018/9/10_10:44
     * @param: 文件名
     * @return: 是否
     */
    public boolean isImgFileName(String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        if (suffix.equals(".jpg") || suffix.equals(".png") || suffix.equals(".jpeg")) {
            return true;
        }
        return false;
    }


}
