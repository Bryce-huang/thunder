package fun.bryce.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import fun.bryce.api.entity.SysDept;
import fun.bryce.api.entity.SysDept;
import fun.bryce.api.entity.SysDeptRelation;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysDeptRelationService extends IService<SysDeptRelation>
{

    /**
     * 新建部门关系
     *
     * @param sysDept 部门
     */
    void saveDeptRelation(SysDept sysDept);

    /**
     * 通过ID删除部门关系
     *
     * @param id
     */
    void removeDeptRelationById(Integer id);

    /**
     * 更新部门关系
     *
     * @param relation
     */
    void updateDeptRelation(SysDeptRelation relation);
}
