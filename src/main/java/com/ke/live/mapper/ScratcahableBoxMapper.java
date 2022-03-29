package com.ke.live.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ke.live.entity.ScratchableBox;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
    @Select("select * from scratchablebox where belonging =#{belonging}")
    List<ScratchableBox> findAllByBelonging(String belonging);

    @Insert("Insert into scratchablebox values()")
    void newLabel(ScratchableBox scratchableBox);
}
