package service.orders;

import connection.ConnectionSingleton;
import model.Customer;
import model.Orders;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements  IOrderService{
    Connection connection = ConnectionSingleton.getConnection();


    @Override
    public List<Orders> findAllOrder() {
        List<Orders> ordersList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * from orders");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                double amount = resultSet.getDouble("amount");
                Date order_date = resultSet.getDate("order_date");

                Customer customer =

//

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Orders findOrderById(int id) {
        return null;
    }

    @Override
    public void save(Orders orders) {

    }

    @Override
    public void deleteOrder(int id) {

    }

    @Override
    public void updateOrder(int id, Orders orders) {

    }
}
