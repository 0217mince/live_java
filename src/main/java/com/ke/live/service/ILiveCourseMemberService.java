package com.ke.live.service;

import com.ke.live.entity.LiveCourseMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kzf
 * @since 2022-07-07
 */
public interface ILiveCourseMemberService extends IService<LiveCourseMember> {

    /**
     * 分页业务方法
     * @param search　搜索参数
     * @param current 分页位置
     * @param size 分页大小
     * @return IPage
     */
    IPage<LiveCourseMember> listPage(LiveCourseMember search,Integer current,Integer size);

    /**
     * 不分页业务方法
     * @param search　搜索参数
     * @return List
     */
    List<LiveCourseMember> listAll(LiveCourseMember search);


    /**
     * 根据Id获取对象
     * @param id　主键ID
     * @return LiveCourseMember
     */
    LiveCourseMember get(Integer id);


    /**
     * 全部更新
     * @param entity　更新的对象
     * @return LiveCourseMember
     */
    void update(LiveCourseMember entity);


    /**
     * 部分更新
     * @param entity　更新的对象
     * @return LiveCourseMember
     */
    void patch(LiveCourseMember entity);


    /**
     * 根据Id删除对象
     * @param id　主键ID
     */
    void delete(Integer id);
}
