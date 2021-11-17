package service.orders;

import connection.ConnectionSingleton;
import model.Customer;
import model.Orders;
import model.Product;
import service.customer.CustomerService;
import service.customer.ICustomerService;
import service.product.IProduct;
import service.product.ProductService;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {
    Connection connection = ConnectionSingleton.getConnection();
    ICustomerService iCustomerService = new CustomerService();
    IProduct iProduct = new ProductService();


    @Override
    public List<Orders> findAllOrder() {
        List<Orders> ordersList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT  * from orders");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double amount = resultSet.getDouble("amount");
                Date order_date = resultSet.getDate("order_date");
                int customerId = resultSet.getInt("customerId");
                Customer customer = iCustomerService.findById(customerId);
                boolean status = resultSet.getBoolean("status");

                Orders orders = new Orders(id, amount, order_date, customer, status);
                ordersList.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public Orders findOrderById(int id) {
        Orders orders = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select  * from Module3.Orders where id=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                Date order_date = resultSet.getDate("order_date");
                int customerId = resultSet.getInt("id");
                Customer customer = iCustomerService.findById(customerId);
                Boolean status = resultSet.getBoolean("status");
                orders = new Orders(id, amount, order_date, customer, status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public void save(Orders orders) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Module3.Orders(amount, order_date, customerId, status) VALUES (?,?,?,?);");
            preparedStatement.setDouble(1, orders.getAmount());
            preparedStatement.setDate(2, (Date) orders.getOrder_date());
            preparedStatement.setInt(3, orders.getCustomer().getId());
            preparedStatement.setBoolean(4, orders.isStatus());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrder(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from Module3.orders where id=?;");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateOrder(int id, Orders orders) {
        try {
            PreparedStatement preparedStatement =connection.prepareStatement("update Module3.Orders set amount=?,order_date=?,customerId=?,status=?;");
            preparedStatement.setDouble(1,orders.getAmount());
            preparedStatement.setDate(2,(Date) orders.getOrder_date());
            preparedStatement.setInt(3,orders.getCustomer().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
