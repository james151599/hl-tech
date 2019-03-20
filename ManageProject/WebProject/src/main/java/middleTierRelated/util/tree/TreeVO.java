package middleTierRelated.util.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeVO<T> {

  private T node;

  private List<TreeVO<T>> subNodes;

  public TreeVO(T node) {
    this.node = node;
    subNodes = new ArrayList<>();
  }

  public T getNode() {
    return node;
  }

  public void setNode(T node) {
    this.node = node;
  }

  public List<TreeVO<T>> getSubNodes() {
    return subNodes;
  }

  public void setSubNodes(List<TreeVO<T>> subNodes) {
    this.subNodes = subNodes;
  }
}
