package com.ke.live.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 小凡
 */
@Data
public class ScratchableBoxDTO {
    private Integer categoryId;
    private String belonging;
    private String linkBox;
    private String categoryName;
    private Integer orderNumber;
}
