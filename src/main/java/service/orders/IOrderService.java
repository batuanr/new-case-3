package service.orders;

import model.Customer;
import model.Orders;

import java.util.List;

public interface IOrderService {
    List<Orders> findAllOrder();

    Orders findOrderById(int id);
    Orders getOrder(Customer customer);

    void save(Orders orders);

    void deleteOrder(int id);

    void updateOrder(int id, Orders orders);


}
