package com.ga.cdz.controller.member;

import com.ga.cdz.domain.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author huanghaohao
 * @date 2018-09-07 15:07
 * @desc 会员等级控制层
 */
@RestController
@RequestMapping("/member/rank")
public class MemberRankController {


    @GetMapping("/list")
    public Result getUserList() {

        return Result.success();
    }


}
