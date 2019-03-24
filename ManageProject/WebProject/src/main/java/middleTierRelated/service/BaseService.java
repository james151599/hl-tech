package middleTierRelated.service;

import java.util.Collection;

public interface BaseService<T> {

  boolean exeSaveBatch(Collection<T> entities, int batchSize);
}
