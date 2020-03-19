package com.lysong.friday.base.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: LySong
 * @Date: 2020/3/15 23:08
 */
@Data
public class PageTableRequest implements Serializable {
    /**
     * 当前页数
     */
    private Integer page;
    /**
     * 需要返回的总数据条数
     */
    private Integer limit;
    /**
     * 从第几条开始查找
     */
    private Integer offset;

    /**
     * 计算查找的时候从第几条开始查找
     */
    public void countOffset(){
        if(null == this.page || null == this.limit){
            this.offset = 0;
            return;
        }
        this.offset = (this.page - 1) * limit;
    }
}
