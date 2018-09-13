package com.ga.cdz.controller.system;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.BusinessException;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.dto.admin.ChargingStationDTO;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.entity.ChargingStationAttach;
import com.ga.cdz.domain.group.admin.IMChargingStationGroup;
import com.ga.cdz.domain.vo.admin.ChargingStationFileVo;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMChargingStationAttachService;
import com.ga.cdz.service.IMChargingStationService;
import com.ga.cdz.util.MFileUtil;
import com.google.common.collect.Lists;
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
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author:wanzhongsu
 * @description: 充电站列表管理
 * @date:2018/9/10 15:01
 */
@RestController
@RequestMapping("/charging/station")
public class ChargingStationController extends AbstractBaseController {
    /**
     * 充电站管理服务
     */
    @Resource
    IMChargingStationService mChargingStationService;
    /**
     * 充电站附件服务
     */
    @Resource
    IMChargingStationAttachService mChargingStationAttachService;
    /**
     * 文件工具类
     */
    @Resource
    MFileUtil mFileUtil;
    /**
     * 站点图片路径
     */
    @Value("${file.station}")
    protected String userAvatarFilePath;

    /**
     * @author:wanzhongsu
     * @description: 分页获取充电站列表信息
     * @date: 2018/9/10 16:26
     * @param: PageVo
     * @return: Result
     */
    @PostMapping("/list")
    public Result getStationList(@RequestBody @Validated PageVo<ChargingStationVo> vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        IPage<ChargingStationDTO> iPage = mChargingStationService.getStationPage(vo);
        return Result.success().data(iPage);
    }

    /**
     * @author:wanzhongsu
     * @description: 添加充电站信息
     * @date: 2018/9/10 16:27
     * @param: ChargingStationVo
     * @return: Result
     */
    @PostMapping("/add")
    public Result saveStation(@RequestBody @Validated(value = IMChargingStationGroup.Add.class) ChargingStationVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        int result = mChargingStationService.saveStation(vo);
        if (result > 0) {
            return Result.success().message("保存成功");
        }
        return Result.fail().message("保存失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 充电站文件上传及信息添加
     * @date: 2018/9/12 18:23
     * @param: MultipartFile ChargingStationFileVo
     * @return: Result
     */
    @PostMapping("/file/upload")
    @Transactional
    public Result uploadFile(@RequestParam("files") MultipartFile[] multipartFiles, ChargingStationFileVo fileVo) {
        if (ObjectUtils.isEmpty(multipartFiles) || multipartFiles.length == 0) {
            throw new BusinessException("文件不能为空");
        }
        /**上传图片成功的计数*/
        int successCount = 0;
        //保存成功后的StationID
        ChargingStationVo vo = new ChargingStationVo();
        BeanUtils.copyProperties(fileVo, vo);
        if (fileVo.getStationType() == 1) {
            vo.setStationType(ChargingStation.StationType.OUTSIDE);
        } else if (fileVo.getStationType() == 2) {
            vo.setStationType(ChargingStation.StationType.INSIDE);
        } else if (fileVo.getStationType() == 3) {
            vo.setStationType(ChargingStation.StationType.PERSON);
        }
        int stationId = saveChargingStation(vo);
        /**最后保存到数据库的文件路径集合*/
        List<String> dbFilePaths = Lists.newArrayList();
        for (MultipartFile fileItem : multipartFiles) {
            String fileName = fileItem.getOriginalFilename();
            /**判断是不是文件名*/
            boolean isImg = mFileUtil.isImgFileName(fileName);
            if (!isImg) {
                throw new BusinessException("键值数据保存成功，但是文件不是图片文件");
            }
            /**生成新的文件名*/
            String newFileName = mFileUtil.renameFile(fileName);
            /**生成新的文件目录*/
            String newFilePath = mFileUtil.getTimePath() + File.separator + stationId + File.separator;
            /**保存在数据库的dbFilePath*/
            String dbFilePath = newFilePath + newFileName;
            /**保存文件*/
            String filePathAndName = userAvatarFilePath + dbFilePath;
            boolean isSuccess = mFileUtil.saveFile(fileItem, filePathAndName);
            if (!isSuccess) {
                throw new BusinessException("键值数据保存成功，但是文件上传失败");
            }
            dbFilePaths.add(dbFilePath);
            successCount++;
        }
        /**判断文件是否全部上传成功*/
        if (successCount != multipartFiles.length) {
            throw new BusinessException("键值数据保存成功，但是上传文件失败");
        }
//        /***字符串我们全部用分号拼接,且不要最后一份分号*/
//        String dbFilePathStr = String.join(";", dbFilePaths);
        //创建充电站附件对象
        ChargingStationAttach chargingStationAttach = new ChargingStationAttach();
        chargingStationAttach.setStationId(stationId);
        int i = 1;
        for (String path : dbFilePaths) {
            chargingStationAttach.setAttachIdx(i);
            chargingStationAttach.setAttachPath(path);
            i = i + 1;
            //将字符串文件路径保存
            try {
                mChargingStationAttachService.save(chargingStationAttach);
            } catch (Exception e) {
                e.printStackTrace();
                throw new BusinessException("文件写入数据库失败");
            }
        }
        return Result.success().message("键值数据保存成功，文件上传成功");
    }

    /**
     * @author:wanzhongsu
     * @description: 保存ChargingStationVo对象，只限于本类中调用
     * @date: 2018/9/12 18:24
     * @param:
     * @return:
     */
    private int saveChargingStation(ChargingStationVo vo) {
        //充电站信息验证
        Integer shopId = vo.getShopId();
        String stationCode = vo.getStationCode();
        String stationName = vo.getStationName();
        Integer sttpeId = vo.getSttpeId();
        Integer operatorsId = vo.getOperatorsId();
        if (ObjectUtils.isEmpty(shopId)) {
            throw new BusinessException("商户ID不能为空");
        } else if (ObjectUtils.isEmpty(stationCode)) {
            throw new BusinessException("充电站编码不能为空");
        } else if (ObjectUtils.isEmpty(stationName)) {
            throw new BusinessException("充电站名称不能为空");
        } else if (ObjectUtils.isEmpty(sttpeId)) {
            throw new BusinessException("运营商类型ID不能为空");
        } else if (ObjectUtils.isEmpty(operatorsId)) {
            throw new BusinessException("运营商ID不能为空");
        }
        if (!Pattern.matches(RegexConstant.STATION_CODE, stationCode)) {
            throw new BusinessException("充电站编码格式不对");
        }
        //保存并返回充电站ID
        int stationId = mChargingStationService.saveStation(vo);
        return stationId;
    }

    /**
     * @author:wanzhongsu
     * @description: 删除充电站信息
     * @date: 2018/9/10 16:27
     * @param: ChargingStationVo
     * @return: Result
     */
    @PostMapping("/delete")
    public Result deleteStation(@RequestBody @Validated(value = IMChargingStationGroup.Delete.class) ChargingStationVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingStationService.deleteStationById(vo);
        if (result) {
            return Result.success().message("删除成功");
        }
        return Result.fail().message("删除失败");
    }

    /**
     * @author:wanzhongsu
     * @description: 修改充电站信息
     * @date: 2018/9/10 16:28
     * @param: ChargingStationVo
     * @return: Result
     */
    @PostMapping("/update")
    public Result updateStation(@RequestBody @Validated(value = IMChargingStationGroup.Update.class) ChargingStationVo vo, BindingResult bindingResult) {
        checkParams(bindingResult);
        boolean result = mChargingStationService.updateStationById(vo);
        if (result) {
            return Result.success().message("修改成功");
        }
        return Result.fail().message("修改失败");
    }

}

