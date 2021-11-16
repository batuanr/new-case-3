package service.style;

import connection.ConnectionSingleton;
import model.Style;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StyleServiceImpl implements IStyleService {
    private static Connection connection = ConnectionSingleton.getConnection();

    @Override
    public List<Style> showAll() {
        List<Style> styleList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select *from style");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                styleList.add(new Style(id, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return styleList;
    }

    @Override
    public Style findByID(int id) {
        Style style = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select id,name from style where id =?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                style = new Style(id, name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return style;
    }


    @Override
    public boolean create(Style style) {
        boolean rowCreateStyle = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into style (name)value (?)");
            preparedStatement.setString(1, style.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowCreateStyle;
    }

    @Override
    public boolean update(Style style) {
        boolean rowUpdateStyle = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update style set name =? where id=?");
            preparedStatement.setInt(1, style.getId());
            preparedStatement.setString(1, style.getName());
            rowUpdateStyle = preparedStatement.executeLargeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdateStyle;
    }

    @Override
    public boolean delete(int id) {
      boolean rowDeleteStyle = false;
      try {
          PreparedStatement preparedStatement =
                  connection.prepareStatement("delete from style where id =?");
          preparedStatement.setInt(1,id);
          rowDeleteStyle =preparedStatement.executeUpdate()>0;
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }
        return rowDeleteStyle;
    }
}
