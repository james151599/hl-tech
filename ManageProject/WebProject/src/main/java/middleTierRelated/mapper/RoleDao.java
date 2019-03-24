package middleTierRelated.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import middleTierRelated.entity.RolePO;
import middleTierRelated.vo.RoleVO;

public interface RoleDao extends BaseDao<RolePO> {

  List<RoleVO> selectAll();

  RoleVO selectById(@Param("id") String id);

  int insert(RolePO role);

  int updateById(RolePO role);

  int deleteById(@Param("id") String id);
}
