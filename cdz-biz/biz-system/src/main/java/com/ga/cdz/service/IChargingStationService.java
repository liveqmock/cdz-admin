package com.ga.cdz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.api.ChargingStationCommentDTO;
import com.ga.cdz.domain.dto.api.ChargingStationDetailDTO;
import com.ga.cdz.domain.dto.api.ChargingStationPageDTO;
import com.ga.cdz.domain.dto.api.ChargingStationTerminalDTO;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.api.ChargingStationPageVo;
import com.ga.cdz.domain.vo.api.ChargingStationVo;

import java.util.List;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/11_15:31
 */
public interface IChargingStationService extends IService<ChargingStation> {

    /**
     * @author: liuyi
     * @description: 获取首页的充电站信息
     * @date: 2018/9/11_15:51
     * @param:
     * @return:
     */
    List<ChargingStationPageDTO> getMainStationPage(ChargingStationPageVo vo);

    /**
     * @author: liuyi
     * @description: 获取附近的充电站信息
     * @date: 2018/9/16_09:40
     * @param:
     * @return:
     */
    List<ChargingStationPageDTO> getNearStationPage(ChargingStationPageVo vo);

    /**
     * @Author: liuyi
     * @Description: 获取充电站信息
     * @Date: 2018/9/17_14:53
     * @param vo ChargingStationVo
     * @return ChargingStationDTO
     */
    ChargingStationDetailDTO getChargingStationDetail(ChargingStationVo vo);

     /**
      * @Author: liuyi
      * @Description: 获取充电站终端
      * @Date: 2018/9/17_15:44
      * @param vo ChargingStationVo
      * @return List<ChargingStationTerminalDTO>
      */
     List<ChargingStationTerminalDTO> getChargingStationTerminal(ChargingStationVo vo);

     /**
      * @Author: liuyi
      * @Description: 获取充电站评论
      * @Date: 2018/9/17_16:55
      * @param vo ChargingStationVo
      * @return List<ChargingStationCommentDTO>
      */
     List<ChargingStationCommentDTO> getChargingStationComment(ChargingStationVo vo);

}
