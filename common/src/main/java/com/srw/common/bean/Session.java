package com.srw.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: renwei.song
 * @Date: 2021/4/7 16:34
 */
@Data
@AllArgsConstructor
public class Session implements Serializable {

    private static final long serialVersionUID = -5440930338676887565L;

    private Long userId;

}
