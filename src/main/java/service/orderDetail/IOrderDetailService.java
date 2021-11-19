package service.orderDetail;

import model.OrderDetail;
import model.Orders;
import model.Style;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetail> showAll();
    List<OrderDetail> findByOrder(int customerId);
    OrderDetail findByID(int id);

    boolean create(OrderDetail orderDetail);

    boolean update(OrderDetail orderDetail);

    boolean delete(int id);
}
