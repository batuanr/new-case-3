package service.product;

import connection.ConnectionSingleton;
import model.Product;
import model.Size;
import model.Style;
import model.Type;
import service.Type.ITypeService;
import service.Type.TypeService;
import service.size.ISize;
import service.size.SizeService;
import service.style.IStyleService;
import service.style.StyleServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProduct{
    Connection connection = ConnectionSingleton.getConnection();
    IStyleService styleService = new StyleServiceImpl();
    ISize sizeService = new SizeService();
    ITypeService typeService = new TypeService();
    @Override
    public  List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Product");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int typeId = resultSet.getInt("typeID");
                Type type = typeService.findTypeById(typeId);
                int styleId = resultSet.getInt("StyleId");
                Style style = styleService.findByID(styleId);
                double price = resultSet.getDouble("price");
                List<Size> sizeList = sizeService.findByProductId(id);
                Product product = new Product(id, name, type, style, price, sizeList);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
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
