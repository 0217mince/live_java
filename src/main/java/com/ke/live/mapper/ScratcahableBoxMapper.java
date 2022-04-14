package com.ke.live.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ke.live.entity.ScratchableBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author 小凡
 */
@Mapper
public interface ScratcahableBoxMapper extends BaseMapper<ScratchableBox> {

    /**
     * 查找所有标签
     * @param belonging 所属
     * @return {@link ScratchableBox}
     */
    @Select("select * from scratchablebox where belonging =#{belonging} order By order_number desc")
    List<ScratchableBox> findAllByBelonging(String belonging);

    /**
     * 更新标签
     * @param scratchableBox 标签信息
     */
    @Update("update scratchablebox set link_box = #{linkBox} ,category_name =#{categoryName} ,order_number =#{orderNumber} where category_id =#{categoryId}")
    void updateLabel(ScratchableBox scratchableBox);

    @Select("select * from scratchablebox where category_id =#{categoryId}")
    ScratchableBox selectById(Integer categoryId);

    @Select("select * from scratchablebox where link_box = #{linkBox}")
    ScratchableBox selectByKey(String linkBox);
}
