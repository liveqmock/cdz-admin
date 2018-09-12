package com.ga.cdz.domain.vo.base;

import com.ga.cdz.constant.RegexConstant;
import com.ga.cdz.domain.entity.Operators;
import com.ga.cdz.domain.group.admin.IMOperatorsGroup;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

/**
 * @author:wanzhongsu
 * @description: 运营商信息vo
 * @date:2018/9/10 9:27
 */
@Data
@Accessors(chain = true)
public class OperatorsVo {
    /**
     * 运营商ID
     */
    @NotNull(groups = {IMOperatorsGroup.Delete.class}, message = "运营商ID不能为空")
    private Integer operatorsId;
    /**
     * 运营商名称
     */
    @NotBlank(groups = {IMOperatorsGroup.Add.class}, message = "运营商名称不能为空")
    private String operatorsName;
    /**
     * 运营商编码
     */
    @NotBlank(groups = {IMOperatorsGroup.Add.class}, message = "运营商编码不能为空")
    @Pattern(groups = {IMOperatorsGroup.Add.class}, regexp = RegexConstant.REGEX_CODE, message = "运营商编码只能是两位数字")
    private String operatorsCode;
    /**
     * 运营商状态 0 删除  1 正常
     */
    private Operators.OperatorsState operatorsState;
    /**
     * 插入时间
     */
    private LocalDateTime insertDt;

}
