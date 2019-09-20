package instanceBean.noScanBean.simulateMybatis.mappers;

import org.apache.ibatis.annotations.Select;

public interface MyMapper {

  @Select("select count(*) from dual")
  int selectCount();
}
