package middleTierRelated.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface BaseDao<T> {

  @Select("select column_name from information_schema.COLUMNS where TABLE_NAME=#{tableName}")
  List<String> fetchTableColumns(@Param("tableName") String tableName);

  int saveBatch(@Param("statement") String statement);
}
