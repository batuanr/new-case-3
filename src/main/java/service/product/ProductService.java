package service.product;

import connection.ConnectionSingleton;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProduct{
    Connection connection = ConnectionSingleton.getConnection();

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Product");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product findById(int productId) {
        return null;
    }

    @Override
    public void save(Product product, int[] sizeList) {

    }

    @Override
    public List<Product> findByTypeID(int typeID) {
        return null;
    }

    @Override
    public List<Product> findByStyleID(int styleID) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Product product) {

    }
}
