package service.Type;

import java.util.List;

public interface ITypeService<T> {

    List<T> findAllType();

    T findTypeById(int id);
}
