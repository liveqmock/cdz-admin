package com.ga.cdz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.ga.cdz.domain.dto.admin.AdminDemoDTO;
import com.ga.cdz.domain.vo.base.PageVo;
import com.ga.cdz.service.IMAdminInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CdzAdminApplicationTests {

    @Resource
    IMAdminInfoService mAdminInfoService;

    @Test
    public void contextLoads() {
    }

    /**
     *  关联查询分页测试
     * */
    @Test
    public void adminDemoDTO(){
        PageVo<AdminDemoDTO> pageVo=new PageVo<>();
        pageVo.setIndex(1).setSize(10);
        IPage<AdminDemoDTO> page=mAdminInfoService.getAdminDemoDTOPage(pageVo);
        System.out.println(page);
    }

}
