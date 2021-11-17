package service.Type;

import model.Type;

import java.util.List;

public interface ITypeService {

    List<Type> findAllType();

    Type findTypeById(int id);
}
