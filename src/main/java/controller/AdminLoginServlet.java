package controller;

import model.Admin;
import service.admin.AdminService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminLoginServlet", value = "/admin")
public class AdminLoginServlet extends HttpServlet {
    AdminService adminService = new AdminService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
}