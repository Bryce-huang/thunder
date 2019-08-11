package fun.bryce.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import fun.bryce.api.entity.SysDept;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author bryce
 * @since 2019/07/1
 */
public interface SysDeptMapper extends BaseMapper<SysDept>
{

    /**
     * 关联dept——relation
     *
     * @return 数据列表
     */
    List<SysDept> listDepts();
}
