package middleTierRelated.service.impl;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import middleTierRelated.mapper.BaseDao;
import middleTierRelated.service.BaseService;
import middleTierRelated.tool.TableName;

public class BaseServiceImpl<T, K extends BaseDao<T>> implements BaseService<T> {

  private static final int BATCH_SIZE = 3;

  @Autowired
  K subDao;

  public boolean exeSaveBatch(Collection<T> entities) {
    return exeSaveBatch(entities, BATCH_SIZE);
  }

  @Override
  public boolean exeSaveBatch(Collection<T> entities, int batchSize) {
    try {
      if (!entities.getClass().isAnnotationPresent(TableName.class)) {
        throw new RuntimeException("can't execute batch save");
      }

      String tableName = entities.getClass().getAnnotation(TableName.class).value();
      List<String> columns = subDao.fetchTableColumns(tableName);
      Iterator<T> iterator = entities.iterator();
      T entity = null;
      Field field = null;
      StringBuilder values = new StringBuilder();
      int count = 1;
      String sql = "insert into " + tableName + " values";

      while (iterator.hasNext()) {
        entity = iterator.next();
        values.append("(");
        for (String col : columns) {
          field = entity.getClass().getDeclaredField(columnToProperty(col));
          field.setAccessible(true);
          values.append("'").append(field.get(entity)).append("',");
        }
        values.deleteCharAt(values.lastIndexOf(","));
        values.append("),");
        if (count % batchSize == 0) {
          subDao.saveBatch(sql + values.deleteCharAt(values.lastIndexOf(",")));
          values.setLength(0);
          count = 0;
        }
        count++;
      }
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      e.printStackTrace();
      throw new RuntimeException("batch save exception");
    }

    return true;
  }

  private String columnToProperty(String columnName) {
    int index = columnName.indexOf("_");
    String letter = String.valueOf(columnName.charAt(index + 1));
    String result = columnName.replaceFirst("_" + letter, letter.toUpperCase());

    return result;
  }
}
