package controller;

import model.Customer;
import model.Product;
import service.customer.CustomerService;
import service.customer.ICustomerService;
import service.product.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", value = "/customers")
public class CustomerServlet extends HttpServlet {
    CustomerService customerService = new CustomerService();
    ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "register":
                showRegister(request, response);
                break;
            case "info":
                showInfo(request, response);
            case "home":
                backHome(request, response);
            default:

        }
    }

    private void backHome(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/homeCustomer.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showInfo(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        Customer customer = customerService.findByEmail(email);
        request.setAttribute("customer", customer);
        request.setAttribute("email", email);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/info.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showRegister(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer/register.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        if (customerService.checkAccount(email, pass) == true) {
            request.setAttribute("email", email);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/homeCustomer.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            String mess = "NOT EXIST";
            request.setAttribute("mess", mess);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer/customerLogin.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                login(request, response);
            case "register":
                register(request, response);
            case "info":
                editCustomer(request, response);
            default:
        }
    }

    private void editCustomer(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        Customer customer1 = customerService.findByEmail(email);
        int id = customer1.getId();
        Customer customer = new Customer(name, phone, email, pass);
        customerService.update(id, customer);
        request.setAttribute("email", email);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("product/homeCustomer.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
        Customer customer = new Customer(name, phone, email, pass);
        customerService.save(customer);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer/register.jsp");
        request.setAttribute("message", "tao thanh cong");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
