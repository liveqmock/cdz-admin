package com.ga.cdz.controller.banner;

import com.ga.cdz.controller.AbstractBaseController;
import com.ga.cdz.domain.bean.Result;
import com.ga.cdz.domain.entity.Banner;
import com.ga.cdz.service.IBannerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/11_14:24
 */
@RestController
@RequestMapping("/banner")
public class BannerController extends AbstractBaseController {

    //获得IBannerService对象
    @Resource
    private IBannerService iBannerService;

    @PostMapping("/list")
    public Result getListAllBanner() {
        List<Banner> banners = iBannerService.getListAllBanner();
        return Result.success().data(banners);
    }

}
