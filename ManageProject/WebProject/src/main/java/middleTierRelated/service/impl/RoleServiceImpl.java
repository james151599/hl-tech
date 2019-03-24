package middleTierRelated.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import middleTierRelated.entity.RolePO;
import middleTierRelated.mapper.RoleDao;
import middleTierRelated.service.RoleService;
import middleTierRelated.vo.RoleVO;

public class RoleServiceImpl extends BaseServiceImpl<RolePO, RoleDao> implements RoleService {

  @Autowired
  private RoleDao roleDao;

  @Override
  public List<RoleVO> getAll() {
    return roleDao.selectAll();
  }

  @Override
  public RoleVO getById(String id) {
    return roleDao.selectById(id);
  }

  @Override
  public int save(RolePO role) {
    return roleDao.insert(role);
  }

  @Override
  public int alter(RolePO role) {
    return roleDao.updateById(role);
  }

  @Override
  public int removeById(String id) {
    return roleDao.deleteById(id);
  }

}
