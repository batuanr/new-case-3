package controller;

import model.Customer;
import model.OrderDetail;
import service.customer.CustomerService;
import service.customer.ICustomerService;
import service.orderDetail.IOrderDetailService;
import service.orderDetail.OrderDetailServiceImpl;
import service.orders.IOrderService;
import service.orders.OrderService;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "orderList", value = "/orderList")
public class orderList extends HttpServlet {
    ProductService productService = new ProductService();
    ICustomerService customerService = new CustomerService();
    IOrderService orderService = new OrderService();
    IOrderDetailService orderDetailService = new OrderDetailServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
listOrder(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        listOrder(request, response);
    }
    private void listOrder(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        Customer customer = customerService.findByEmail(email);
        List<OrderDetail> orderDetails = orderDetailService.findByOrder(customer.getId());
        request.setAttribute("list", orderDetails);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orders/orderList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
