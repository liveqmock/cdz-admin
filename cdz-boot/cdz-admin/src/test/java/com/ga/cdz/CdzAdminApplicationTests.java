package com.ga.cdz;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ga.cdz.domain.dto.admin.AdminDemoDTO;
import com.ga.cdz.domain.dto.admin.ChargingDeviceDTO;
import com.ga.cdz.domain.entity.ChargingDevice;
import com.ga.cdz.domain.vo.admin.ChargingDeviceVo;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMAdminInfoService;
import com.ga.cdz.service.IMChargingDeviceService;
import com.ga.cdz.util.MPushUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class CdzAdminApplicationTests {

    @Resource
    IMAdminInfoService mAdminInfoService;

    @Resource
    MPushUtil mPushUtil;

    @Resource
    IMChargingDeviceService mChargingDeviceService;

//    @Test
//    public void contextLoads() {
//    }

    /**
     * 测试充电桩联表查询
     */
    @Test
    public void testChargingDevice(){
        ChargingDeviceVo chargingDeviceVo=new ChargingDeviceVo();
        chargingDeviceVo.setStationId(1);
        List<ChargingDeviceDTO> list=mChargingDeviceService.getChargingDeviceList(chargingDeviceVo);
        for(int i=0;i<list.size();i++){
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(list.get(i).toString());
        }
    }


//    /**
//     * 关联查询分页测试
//     */
//
//    @Test
//    public void adminDemoDTO() {
//        PageVo<AdminDemoDTO> pageVo = new PageVo<>();
//        pageVo.setIndex(1).setSize(1);
//        Page<AdminDemoDTO> page = mAdminInfoService.getAdminDemoDTOPage(pageVo);
//        log.info("page.total=>{}", page.getTotal());
//    }
//
//    /**
//     * 关于推送的测试
//     */
//    @Test
//    public void jpushTest() {
//        mPushUtil.sendAll("jpushTest，来自代码的推送");
//    }
}
