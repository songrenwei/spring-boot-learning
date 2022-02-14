package com.srw.common.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/3/22 20:48
 */
@Data
@AllArgsConstructor
public class UserDto {

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("年龄")
    private Integer age;

}
