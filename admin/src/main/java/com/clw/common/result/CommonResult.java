package com.clw.common.result;

import com.clw.common.constant.MyConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: clw
 * @Description: 通用返回结果
 * @Date: 2020/7/21 22:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public static <T> CommonResult<T> success(String message, T data) {
        return new CommonResult<>(MyConstant.SUCCESS_CODE, message, data);
    }

    public static <T> CommonResult<T> fail(Integer code, String message, T data) {
        return new CommonResult<>(code, message, data);
    }
}
