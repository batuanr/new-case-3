package service.Type;

import connection.ConnectionSingleton;
import model.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeService implements ITypeService {

    Connection connection = ConnectionSingleton.getConnection();
    @Override
    public List<Type> findAllType() {

       List<Type> typeList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * FROM Module3.Type");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
               typeList.add(new Type(id,type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeList;
    }

    @Override
    public Type findTypeById(int id) {
        Type typeProduct = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from Module3.type where id=?;");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String type = resultSet.getString("type");
                typeProduct = new Type(id,type);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return typeProduct;
    }


}
