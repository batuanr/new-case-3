package controller;

<<<<<<< HEAD
import model.*;
import service.customer.CustomerService;
import service.customer.ICustomerService;
import service.orderDetail.IOrderDetailService;
import service.orderDetail.OrderDetailServiceImpl;
import service.orders.IOrderService;
import service.orders.OrderService;
=======
import model.Product;
import model.Size;
>>>>>>> d3ae2b4675b75172344d88aa7404385939236d4a
import service.product.ProductService;
import service.size.SizeService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    SizeService sizeService = new SizeService();
    ProductService productService = new ProductService();
<<<<<<< HEAD
    ICustomerService customerService = new CustomerService();
    IOrderService orderService = new OrderService();
    IOrderDetailService orderDetailService = new OrderDetailServiceImpl();
=======
>>>>>>> d3ae2b4675b75172344d88aa7404385939236d4a

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "selectS":
                listByStyle(request, response);
                break;
            case "selectG":
                listByType(request, response);
                break;
            case "detail":
                fromProductDetail(request, response);
<<<<<<< HEAD
                break;
            case "addOrder":
                addOrder(request, response);
=======
>>>>>>> d3ae2b4675b75172344d88aa7404385939236d4a
                break;

            default:

        }
    }

    private void fromProductDetail(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        int id = Integer.parseInt(request.getParameter("productId"));
        Product product = productService.findById(id);
        request.setAttribute("product",product);
        request.setAttribute("email",email);
        List<Size> sizeList = sizeService.findByProductId(id);
        request.setAttribute("sizeList",sizeList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orders/detailProducts.jsp");

        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listByType(HttpServletRequest request, HttpServletResponse response) {
        int type = Integer.parseInt(request.getParameter("typeId"));
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        List<Product> productList = productService.findByTypeID(type);
        request.setAttribute("listProduct", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/productList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listByStyle(HttpServletRequest request, HttpServletResponse response) {
        int styleId = Integer.parseInt(request.getParameter("styleId"));
        List<Product> productList = productService.findByStyleID(styleId);
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        request.setAttribute("listProduct", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/productList.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

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
            response.sendRedirect("/products");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
<<<<<<< HEAD
//            case "addOrder": addOrder(request, response);
=======
            case "":
>>>>>>> d3ae2b4675b75172344d88aa7404385939236d4a
            default:

        }
    }
}
