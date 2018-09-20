package com.ga.cdz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ga.cdz.domain.dto.admin.ChargingStationDTO;
import com.ga.cdz.domain.entity.ChargingStation;
import com.ga.cdz.domain.vo.admin.ChargingStationSelectVo;
import com.ga.cdz.domain.vo.base.ChargingStationVo;
import com.ga.cdz.domain.vo.base.PageVo;

import java.util.List;

/**
 * @author:wanzhongsu
 * @description: 充电站列表服务接口
 * @date:2018/9/10 15:08
 */
public interface IMChargingStationService extends IService<ChargingStation> {
    /**
     * @author:wanzhongsu
     * @description: 分页获取充电站信息
     * @date: 2018/9/10 16:29
     * @param: PageVo
     * @return: IPage<ChargingStationDTO>
     */
    IPage<ChargingStationDTO> getStationPage(PageVo<ChargingStationSelectVo> vo, String name);

    /**
     * @author:wanzhongsu
     * @description: 分页获取充电站列表
     * @date: 2018/9/18 9:12
     * @param: PageVo
     * @return: IPage<ChargingStationDTO>
     */
    //todo Cedar 此接口没有使用请删除
    IPage<ChargingStationDTO> getStationPageByCon(PageVo<ChargingStationSelectVo> vo);

    /**
     * @author:wanzhongsu
     * @description: 根据名字模糊查询station的id
     * @date: 2018/9/14 10:36
     * @param: ChargingStationDetailVo
     * @return: List
     */
    List<ChargingStation> getStationList(ChargingStationVo vo);

    /**
     * @author:wanzhongsu
     * @description: 修改充电站信息
     * @date: 2018/9/10 16:29
     * @param: ChargingStationDetailVo
     * @return: 是否修改成功
     */
    boolean updateStationById(ChargingStationVo vo);

    /**
     * @author:wanzhongsu
     * @description: 删除充电站信息
     * @date: 2018/9/10 16:29
     * @param: ChargingStationDetailVo
     * @return: 是否删除成功
     */
    boolean deleteStationById(ChargingStationVo vo);

    /**
     * @author:wanzhongsu
     * @description: 保存充电站信息
     * @date: 2018/9/10 16:30
     * @param: ChargingStation的vo对象
     * @return: 返回保存后的StationId值，否则-1
     */
    int saveStation(ChargingStationVo vo);


}
