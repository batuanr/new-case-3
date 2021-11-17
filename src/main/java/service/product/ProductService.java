package service.product;

import connection.ConnectionSingleton;
import jdk.nashorn.internal.parser.Scanner;
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

import java.sql.*;
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
                String url=resultSet.getString("url");
                Product product = new Product(id, name, type, style, price, sizeList,url);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


    @Override
    public Product findById(int productId) {
        Product product = null;
        try {
            PreparedStatement statement = connection.prepareStatement("select * from Product where id = ?");
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                int typeId = resultSet.getInt("typeID");
                Type type = typeService.findTypeById(typeId);
                int styleId = resultSet.getInt("StyleId");
                Style style = styleService.findByID(styleId);
                double price = resultSet.getDouble("price");
                String url =resultSet.getString("url");
                List<Size> sizeList = sizeService.findByProductId(productId);
                product = new Product(productId, name, type, style, price, sizeList,url);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void save(Product product, int[] sizeList) {
        int productID = 0;
        try {
            PreparedStatement statement = connection.prepareStatement("insert into Product (name , typeID, styleID, price) value (? , ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getType().getId());
            statement.setInt(3, product.getStyle().getId());
            statement.setDouble(4, product.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()){
                productID = resultSet.getInt("id");
            }
            PreparedStatement statement1 = connection.prepareStatement("insert into ProductSize (PRODUCTID, SIZEID) value (? , ?)");
            for (int size: sizeList){
                statement1.setInt(1, productID);
                statement1.setInt(2, size);
                statement1.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findByTypeID(int typeID) {
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select P.id, P.name, P.styleID, P.price from Product P join Type T on T.id = P.typeID where typeID = ?");
            statement.setInt(1, typeID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Type type = typeService.findTypeById(typeID);
                int styleId = resultSet.getInt("StyleId");
                Style style = styleService.findByID(styleId);
                double price = resultSet.getDouble("price");
                String url=resultSet.getString("url");
                List<Size> sizeList = sizeService.findByProductId(id);
                Product product = new Product(id, name, type, style, price, sizeList,url);
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> findByStyleID(int styleID) {
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select P.id, P.name, P.typeID, P.price from Product P join Style S on S.id = P.styleID where styleID = ?");
            statement.setInt(1, styleID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int typeID = resultSet.getInt("typeID");
                Type type = typeService.findTypeById(typeID);
                Style style = styleService.findByID(styleID);
                double price = resultSet.getDouble("price");
                String url=resultSet.getString("url");
                List<Size> sizeList = sizeService.findByProductId(id);
                Product product = new Product(id, name, type, style, price, sizeList,url);
                productList.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("delete from Product where id = ?");
            statement.setInt(1, id);
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement("delete from ProductSize where productID = ?");
            statement1.setInt(1, id);
            statement1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void edit(int id, Product product) {
        try {
            PreparedStatement statement = connection.prepareStatement("update Product set name = ? , typeID = ?, styleID = ?, price = ?where id = ?");
            statement.setString(1, product.getName());
            statement.setInt(2, product.getType().getId());
            statement.setInt(3, product.getStyle().getId());
            statement.setDouble(4, product.getPrice());
            statement.setInt(5, id);
            statement.executeUpdate();
            PreparedStatement statement1 = connection.prepareStatement("delete from ProductSize where productID = ?");
            statement1.setInt(1, id);
            statement1.executeUpdate();
            List<Size> sizeList = sizeService.findByProductId(id);
            int[] size = new int[sizeList.size()];
            for (int i = 0; i < sizeList.size(); i++) {
                size[i] = sizeList.get(i).getId();
            }
            PreparedStatement statement2 = connection.prepareStatement("insert into ProductSize (productID, sizeID) value (?, ?)");
            for (int size2: size){
                statement2.setInt(1, id);
                statement2.setInt(2, size2);
                statement2.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
