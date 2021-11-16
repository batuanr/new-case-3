package service.Style;

import javax.swing.text.Style;
import java.util.List;

public interface ITypeService<T> {

    List<T> findAllType();

    T findTypeById(int id);
}
