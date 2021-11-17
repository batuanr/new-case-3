package service.orderDetail;

import connection.ConnectionSingleton;
import model.OrderDetail;
import model.Orders;
import model.Product;
import model.Style;
import service.product.IProduct;
import service.product.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl implements IOrderDetailService{
    private static Connection connection = ConnectionSingleton.getConnection();
    IProduct productService = new ProductService();

    @Override
    public List<OrderDetail> showAll() {
        List<Orders>ordersList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from orderdetail");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int productID = resultSet.getInt("productID");
                Product product = productService.findById(productID);
                int orderID = resultSet.getInt("orderID");
                ?? chờ định
                int quantity = resultSet.getInt("quantity");
                OrderDetail orderDetail = new OrderDetail(id,product,???,quantity);
                ordersList.add(orderDetail);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }return ordersList;
    }

    @Override
    public OrderDetail findByID(int id) {
       OrderDetail orderDetail = null;
       try {
           PreparedStatement preparedStatement =
                   connection.prepareStatement("select * from oderdetail where id =?");
           preparedStatement.setInt(1,id);
           ResultSet resultSet =preparedStatement.executeQuery();
           while (resultSet.next()){
                int producID = resultSet.getInt("productID");
               Product product = productService.findById(producID);
               int orderID = resultSet.getInt("orderID");
               ??? Chơ định
                int quantity = resultSet.getInt("quantity");
                orderDetail =new OrderDetail(id,product,??,quantity);
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
       return orderDetail;
    }

    @Override
    public boolean create(OrderDetail orderDetail) {
        boolean rowAddOrderDetail = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("insert  into orderdetail (productID,orderID,quantity)value (?,?,?)");
            preparedStatement.setInt(1, orderDetail.getProduct().getId());
            preparedStatement.setInt(2, orderDetail.getOrders().);
            preparedStatement.setInt(3, orderDetail.getQuantity());
            rowAddOrderDetail = preparedStatement.executeUpdate() > 0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } return rowAddOrderDetail;
    }
        @Override
    public boolean update(OrderDetail orderDetail) {
        boolean rowUpdateOrderDetail = false;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("update orderdetail set productidID =?,orderID=?,quantity=? where id=?");
            preparedStatement.setInt(1,orderDetail.getProduct().getId());
            preparedStatement.setInt(2,orderDetail.getOrders().);
            preparedStatement.setInt(3,orderDetail.getQuantity());
            rowUpdateOrderDetail =preparedStatement.executeUpdate()>0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdateOrderDetail;
        }

    @Override
    public boolean delete(int id) {
      boolean rowDeleteOrderDetail=false;
      try {
          PreparedStatement preparedStatement = connection.prepareStatement("delete from orderdetail where id=?");
          preparedStatement.setInt(1,id);
          rowDeleteOrderDetail= preparedStatement.executeUpdate()>0;
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }
      return rowDeleteOrderDetail;
    }
}
