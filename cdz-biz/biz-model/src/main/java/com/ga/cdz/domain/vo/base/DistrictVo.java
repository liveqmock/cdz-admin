package com.ga.cdz.domain.vo.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * @author:wanzhongsu
 * @description: 地区表vo
 * @date:2018/9/10 9:39
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class DistrictVo {
    /**
     * 区域编码
     */
    @NotNull(message = "区域编码不能为空")
    private Integer districtCode;
    /**
     * 地区名称
     */
    private String districtName;
    /**
     * 上一级区域code
     */
    private Integer districtParentCode;

}
