package controller;

import model.*;
import service.Type.ITypeService;
import service.Type.TypeService;
import service.admin.AdminService;
import service.product.IProduct;
import service.product.ProductService;
import service.size.ISize;
import service.size.SizeService;
import service.style.IStyleService;
import service.style.StyleServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdminLoginServlet", value = "/admin")
public class AdminLoginServlet extends HttpServlet {
    AdminService adminService = new AdminService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showLogin(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "login":
                loginAdmin(request, response);
                break;
            default:
        }

    }

    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("account");
        String pass = request.getParameter("pass");
        if (adminService.checkAccount(account, pass) == true) {
            request.setAttribute("account", account);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/management");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            String mess = "NOT EXIST";
            request.setAttribute("mes0s", mess);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin/adminLogin.jsp");
            try {
                requestDispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }


    }
    private void showLogin(HttpServletRequest request, HttpServletResponse response){
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin/adminLogin.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}