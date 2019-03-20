package middleTierRelated.util.tree;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParentPointer {

  String val();

  IdType idType();

  public enum IdType {
    PRIMARYID, PARENTID
  }
}
