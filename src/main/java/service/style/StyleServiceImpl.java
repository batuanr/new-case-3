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
                String style = resultSet.getString("style");
                styleList.add(new Style(id, style));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return styleList;
    }

    @Override
    public Style findByID(int id) {
        Style styles = null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select id, style from style where id =?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String style = resultSet.getString("style");
                styles = new Style(id,style);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return styles;
    }


    @Override
    public boolean create(Style style) {
        boolean rowCreateStyle = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert into style (style)value (?)");
            preparedStatement.setString(1, style.getStyle());
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
                    connection.prepareStatement("update style set style =? where id=?");
            preparedStatement.setInt(1, style.getId());
            preparedStatement.setString(1, style.getStyle());
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
