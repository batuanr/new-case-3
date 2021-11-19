package service.orderDetail;

import connection.ConnectionSingleton;
import model.OrderDetail;
import model.Orders;
import model.Product;
import model.Style;
import service.orders.IOrderService;
import service.orders.OrderService;
import service.product.IProduct;
import service.product.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailServiceImpl implements IOrderDetailService{
    private  Connection connection = ConnectionSingleton.getConnection();
    IProduct productService = new ProductService();
    IOrderService orderService = new OrderService();

    @Override
    public List<OrderDetail> showAll() {
        List<OrderDetail>ordersList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from orderdetail");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                int productID = resultSet.getInt("productID");
                Product product = productService.findById(productID);
                int orderID = resultSet.getInt("orderID");
              Orders orders = orderService.findOrderById(orderID);
                int quantity = resultSet.getInt("quantity");
                OrderDetail orderDetail = new OrderDetail(id,product,orders,quantity);
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
               Orders orders = orderService.findOrderById(orderID);

               int quantity = resultSet.getInt("quantity");
                orderDetail =new OrderDetail(id,product,orders,quantity);
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
            preparedStatement.setInt(2, orderDetail.getOrders().getId());
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
            preparedStatement.setInt(2,orderDetail.getOrders().getId());
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

    @Override
    public List<OrderDetail> findByOrder(int customerId) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("select OD.productID, OD.quantity, O.id from Orders O\n" +
                    "join OrderDetail OD on O.id = OD.orderID\n" +
                    "join Customer C on C.id = O.customerId where customerId = ? and O.status = false");
            statement.setInt(1, customerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int orderID = resultSet.getInt("id");
                int productId = resultSet.getInt("productID");
                int quantity = resultSet.getInt("quantity");
                Product product = productService.findById(productId);
                Orders orders = orderService.findOrderById(orderID);
                OrderDetail orderDetail = new OrderDetail(product, orders, quantity);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
}
