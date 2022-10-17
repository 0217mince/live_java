package com.ke.live.service;

import com.ke.live.entity.HotspotMiniCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 * 小程序热点推荐表 服务类
 * </p>
 *
 * @author kzf
 * @since 2022-07-07
 */
public interface IHotspotMiniCourseService extends IService<HotspotMiniCourse> {

    /**
     * 分页业务方法
     * @param search　搜索参数
     * @param current 分页位置
     * @param size 分页大小
     * @return IPage
     */
    IPage<HotspotMiniCourse> listPage(HotspotMiniCourse search,Integer current,Integer size);

    /**
     * 不分页业务方法
     * @param search　搜索参数
     * @return List
     */
    List<HotspotMiniCourse> listAll(HotspotMiniCourse search);


    /**
     * 根据Id获取对象
     * @param id　主键ID
     * @return HotspotMiniCourse
     */
    HotspotMiniCourse get(Integer id);


    /**
     * 全部更新
     * @param entity　更新的对象
     * @return HotspotMiniCourse
     */
    void update(HotspotMiniCourse entity);


    /**
     * 部分更新
     * @param entity　更新的对象
     * @return HotspotMiniCourse
     */
    void patch(HotspotMiniCourse entity);


    /**
     * 根据Id删除对象
     * @param id　主键ID
     */
    void delete(Integer id);
}
