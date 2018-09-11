package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.BannerMapper;
import com.ga.cdz.domain.entity.Banner;
import com.ga.cdz.service.IBannerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: liuyi
 * @description:
 * @date: 2018/9/11_14:05
 */
@Service("bannerService")
public class BannerService extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

    @Resource
    private BannerMapper bannerMapper;

    @Value("${url.banner}")
    private String position;

    @Override
    public List<Banner> getListAllBanner() {
        List<Banner> banners = bannerMapper.selectList(null);
        for(Banner banner : banners ) {
            String picPosition = position + banner.getSmsPic();
            banner.setSmsPic(picPosition);
        }
        return banners;
    }
}
