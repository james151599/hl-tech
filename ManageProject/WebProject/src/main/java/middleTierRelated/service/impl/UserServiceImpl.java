package middleTierRelated.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import middleTierRelated.entity.UserPO;
import middleTierRelated.mapper.UserDao;
import middleTierRelated.service.UserService;
import middleTierRelated.vo.UserVO;

public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public List<UserVO> getAll() {
    return userDao.selectAll();
  }

  @Override
  public UserVO getById(String id) {
    return userDao.selectById(id);
  }

  @Override
  public UserVO getByLoginCode(String loginCode) {
    return userDao.selectByLoginCode(loginCode);
  }

  @Override
  public int save(UserPO user) {
    return userDao.insert(user);
  }

  @Override
  public int alter(UserPO user) {
    return userDao.update(user);
  }

  @Override
  public int removeById(String id) {
    return userDao.deleteById(id);
  }

}
