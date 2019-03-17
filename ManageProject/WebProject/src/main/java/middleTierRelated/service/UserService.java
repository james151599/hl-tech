package middleTierRelated.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import middleTierRelated.entity.UserPO;
import middleTierRelated.vo.UserVO;

public interface UserService {

  List<UserVO> getAll();

  UserVO getById(@Param("id") String id);

  UserVO getByLoginCode(@Param("loginCode") String loginCode);

  int save(UserPO user);

  int alter(UserPO user);

  int removeById(@Param("id") String id);
}
