package com.ga.cdz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ga.cdz.dao.charging.BannerMapper;
import com.ga.cdz.domain.entity.Banner;
import com.ga.cdz.service.IBannerService;
import com.ga.cdz.util.MUtil;
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
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements IBannerService {

    @Resource
    private BannerMapper bannerMapper;

    //获得图片保存的路径
    @Value("${url.banner}")
    private String position;

    @Resource
    private MUtil mUtil;

    @Override
    public List<Banner> getListAllBanner() {
        List<Banner> banners = bannerMapper.selectList(new QueryWrapper<Banner>().lambda()
                .eq(Banner::getSmsType, Banner.SmsType.AD_SMS));
        //补全图片路径
        for (Banner banner : banners) {
            String picPosition = position + mUtil.urlSeparator(banner.getSmsPic());
            banner.setSmsPic(picPosition);
        }
        return banners;
    }
}
