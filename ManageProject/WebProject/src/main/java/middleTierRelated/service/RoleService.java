package middleTierRelated.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import middleTierRelated.entity.RolePO;
import middleTierRelated.vo.RoleVO;

public interface RoleService extends BaseService<RolePO> {

  List<RoleVO> getAll();

  RoleVO getById(@Param("id") String id);

  int save(RolePO role);

  int alter(RolePO role);

  int removeById(@Param("id") String id);
}
