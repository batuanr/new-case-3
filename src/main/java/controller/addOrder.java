package controller;

import model.Customer;
import model.OrderDetail;
import model.Orders;
import model.Product;
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

@WebServlet(name = "addOrder", value = "/addOrder")
public class addOrder extends HttpServlet {
    ProductService productService = new ProductService();
    ICustomerService customerService = new CustomerService();
    IOrderService orderService = new OrderService();
    IOrderDetailService orderDetailService = new OrderDetailServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        addOrder(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        addOrder(request, response);
    }
    private void addOrder(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        Customer customer = customerService.findByEmail(email);
        Orders orders = orderService.getOrder(customer);
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        OrderDetail orderDetail = new OrderDetail(product, orders, quantity);
        orderDetailService.create(orderDetail);
        try {
            response.sendRedirect("management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
