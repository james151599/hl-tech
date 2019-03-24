package middleTierRelated.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import middleTierRelated.entity.UserPO;
import middleTierRelated.vo.UserVO;

public interface UserDao extends BaseDao<UserPO> {

  List<UserVO> selectAll();

  UserVO selectById(@Param("id") String id);

  UserVO selectByLoginCode(@Param("loginCode") String loginCode);

  int insert(UserPO user);

  int updateById(UserPO user);

  int deleteById(@Param("id") String id);
}
