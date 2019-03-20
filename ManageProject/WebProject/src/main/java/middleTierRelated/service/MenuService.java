package middleTierRelated.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import middleTierRelated.entity.MenuPO;
import middleTierRelated.vo.MenuVO;

public interface MenuService {

  List<MenuVO> getAll();

  MenuVO getById(@Param("id") String id);

  int save(MenuPO menu);

  int alter(MenuPO menu);

  int removeById(@Param("id") String id);
}
