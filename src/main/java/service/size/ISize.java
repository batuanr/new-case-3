package service.size;

import model.Size;

import java.util.List;

public interface ISize {
    List<Size> findAllSize();
    List<Size> findByProductId(int productID);
}
