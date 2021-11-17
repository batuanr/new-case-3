package service.product;

import model.Product;

import java.util.List;

public interface IProduct {
    List<Product> findAll();
    Product findById(int productId);
    void save (Product product, int[] sizeList);
    List<Product> findByTypeID(int typeID);
    List<Product> findByStyleID(int styleID);
    void delete(int id);
    void edit(int id, Product product);
}
