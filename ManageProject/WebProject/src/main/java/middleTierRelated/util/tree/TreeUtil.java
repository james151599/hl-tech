package middleTierRelated.util.tree;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import middleTierRelated.util.tree.ParentPointer.IdType;

public class TreeUtil<T> {

  private String primaryIdFieldName;

  private String parentIdFieldName;

  public TreeUtil(Class<T> cls) {
    Field[] fields = cls.getDeclaredFields();
    ParentPointer pp = null;
    for (Field each : fields) {
      if (each.isAnnotationPresent(ParentPointer.class)) {
        pp = each.getAnnotation(ParentPointer.class);
        System.out.println("customized annotation value: " + pp.val());
        if (IdType.PRIMARYID == pp.idType()) {
          this.primaryIdFieldName = each.getName();
        }
        if (IdType.PARENTID == pp.idType()) {
          this.parentIdFieldName = each.getName();
        }
      }
    }
    if (this.primaryIdFieldName == null || this.parentIdFieldName == null) {
      throw new RuntimeException("Class doesn't fit tree structure");
    }
  }

  public List<TreeVO<T>> fetchTopNodes(final List<T> nodes) {
    List<TreeVO<T>> topNodes = new ArrayList<TreeVO<T>>();
    try {
      Iterator<T> iterator = nodes.iterator();
      T node = null;
      Object parentId = null;
      while (iterator.hasNext()) {
        node = iterator.next();
        parentId = getFieldValue(node, this.parentIdFieldName);
        if (parentId == null || (long) parentId < 0) {
          topNodes.add(new TreeVO<>(node));
          iterator.remove();
        }
      }

      topNodes.forEach((each) -> {
        fetchSubNodes(nodes, each);
      });
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      e.printStackTrace();
      throw new RuntimeException("fetch tree structure exception");
    }

    return topNodes;
  }

  public TreeVO<T> fetchSubNodes(final List<T> nodes, TreeVO<T> parentNode) {
    try {
      Long primaryId = (Long) getFieldValue(parentNode.getNode(), this.primaryIdFieldName);
      T node = null;
      Long parentId = null;

      Iterator<T> iterator = nodes.iterator();
      while (iterator.hasNext()) {
        node = iterator.next();
        parentId = (Long) getFieldValue(node, this.parentIdFieldName);
        if (primaryId.equals(parentId)) {
          parentNode.getSubNodes().add(new TreeVO<>(node));
          iterator.remove();
        }
      }

      parentNode.getSubNodes().forEach((each) -> {
        fetchSubNodes(nodes, each);
      });
    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
        | IllegalAccessException e) {
      e.printStackTrace();
      throw new RuntimeException("fetch tree structure exception");
    }

    return parentNode;
  }

  private Object getFieldValue(T node, String fieldName) throws NoSuchFieldException,
      SecurityException, IllegalArgumentException, IllegalAccessException {
    Field field = node.getClass().getDeclaredField(fieldName);
    field.setAccessible(true);

    return field.get(node);
  }
}
