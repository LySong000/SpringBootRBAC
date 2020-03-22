package com.lysong.friday.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LySong
 * @Date: 2020/3/21 19:39
 */
@RestController
public class DruidStatController {

    @GetMapping("/druid/stat")
    public Object druidStat(){
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
