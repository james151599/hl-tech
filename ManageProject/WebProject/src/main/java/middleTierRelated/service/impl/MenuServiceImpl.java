package middleTierRelated.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import middleTierRelated.entity.MenuPO;
import middleTierRelated.mapper.MenuDao;
import middleTierRelated.service.MenuService;
import middleTierRelated.vo.MenuVO;

public class MenuServiceImpl implements MenuService {

  @Autowired
  private MenuDao menuDao;

  @Override
  public List<MenuVO> getAll() {
    return menuDao.selectAll();
  }

  @Override
  public MenuVO getById(String id) {
    return menuDao.selectById(id);
  }

  @Override
  public int save(MenuPO menu) {
    return menuDao.insert(menu);
  }

  @Override
  public int alter(MenuPO menu) {
    return menuDao.updateById(menu);
  }

  @Override
  public int removeById(String id) {
    return menuDao.deleteById(id);
  }

}
