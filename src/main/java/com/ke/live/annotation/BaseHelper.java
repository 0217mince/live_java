package com.ke.live.annotation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小凡
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseHelper<T> {
    private String code;
    private String msg;
    private T time;
}
