package com.ga.cdz.domain.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:luqi
 * @description: 集合分页类
 * @date:2018/9/13_17:40
 * @param:
 * @return:
 */
@Data
@Accessors(chain = true)
public class Paging<T> {
    //当前页
    private Integer pageNum;
    //总记录数
    private Integer total;
    //总页数
    private Integer pages;

    //当前页显示多少条记录
    @JsonIgnore
    private Integer pageSize;

    private List<T> list;

    public Paging(List<T> list, Integer pageNum, Integer pageSize) {

        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = list.size();

        //总记录数和每页显示的记录之间是否可以凑成整数（pages）
        boolean full = total % pageSize == 0;

        //分页 == 根据pageSize（每页显示的记录数）计算pages
        if (!full) {
            //如果凑不成整数
            this.pages = total / pageSize + 1;
        } else {
            //如果凑成整数
            this.pages = total / pageSize;
        }

        int fromIndex = 0;
        int toIndex = 0;
        fromIndex = pageNum * pageSize - pageSize;
        if (pageNum == 0) {
            throw new ArithmeticException("第0页无法展示");
        } else if (pageNum > pages) {
            //如果查询的页码数大于总的页码数，list设置为[]
            list = new ArrayList<>();
        } else if (pageNum == pages) {
            //如果查询的当前页等于总页数，直接索引到total处
            toIndex = total;
        } else {
            //如果查询的页码数小于总页数，不用担心切割List的时候toIndex索引会越界，直接等
            toIndex = pageNum * pageSize;
        }

        if (list.size() == 0) {
            this.list = list;
        } else {
            this.list = list.subList(fromIndex, toIndex);
        }

    }


}
