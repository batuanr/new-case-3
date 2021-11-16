package service.size;

import connection.ConnectionSingleton;
import model.Size;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SizeService implements ISize{
    public static final String SELECT_ALL_SIZE = "select * from Size";
    public static final String SELECT_SIZE_BY_PRODUCT_ID = "select S.id, S.size from Size S join ProductSize PS on S.id = PS.sizeID and productID = ?";
    Connection connection = ConnectionSingleton.getConnection();
    @Override
    public List<Size> findAllSize() {
        List<Size> sizeList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SIZE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String size = resultSet.getString("size");
                sizeList.add(new Size(id, size));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizeList;
    }

    @Override
    public List<Size> findByProductId(int productID) {
        List<Size> sizeList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_SIZE_BY_PRODUCT_ID);
            statement.setInt(1, productID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String size = resultSet.getString("size");
                sizeList.add(new Size(id, size));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sizeList;
    }
}
