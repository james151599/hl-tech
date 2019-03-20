package middleTierRelated.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import middleTierRelated.entity.MenuPO;
import middleTierRelated.vo.MenuVO;

public interface MenuDao {

  List<MenuVO> selectAll();

  MenuVO selectById(@Param("id") String id);

  int insert(MenuPO role);

  int updateById(MenuPO role);

  int deleteById(@Param("id") String id);
}
