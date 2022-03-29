package com.ke.live.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author 小凡
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "scratchablebox")
public class ScratchableBox {
    @TableId(value = "category_id",type = IdType.AUTO)
    private Integer categoryId;
    @TableField("belonging")
    private String belonging;
    @TableField("link_box")
    private String linkBox;
    @TableField("category_name")
    private String categoryName;
    @TableField("order_number")
    private Integer orderNumber;
    @TableField("create_date")
    private Date createDate;
    @TableField("last_modify")
    private Date lastModify;
}
