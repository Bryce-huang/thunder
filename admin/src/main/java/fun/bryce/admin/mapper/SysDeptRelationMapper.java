package fun.bryce.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.bryce.api.entity.SysDeptRelation;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysDeptRelationMapper extends BaseMapper<SysDeptRelation>
{
    /**
     * 删除部门关系表数据
     *
     * @param id 部门ID
     */
    void deleteDeptRelationsById(Integer id);

    /**
     * 更改部分关系表数据
     *
     * @param deptRelation
     */
    void updateDeptRelations(SysDeptRelation deptRelation);

}
