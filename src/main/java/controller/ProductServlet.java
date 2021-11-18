package controller;

import model.Product;
import service.product.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    ProductService productService=new ProductService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "selectS":
                listByStyle(request,response);
                break;
            case "selectG":
                listByType(request,response);
                break;
            default:

        }
    }

    private void listByType(HttpServletRequest request, HttpServletResponse response) {
        int type=Integer.parseInt(request.getParameter("typeId"));
        String email=request.getParameter("email");
        request.setAttribute("email",email);
        List<Product> productList=productService.findByTypeID(type);
        request.setAttribute("listProduct",productList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/productList.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listByStyle(HttpServletRequest request, HttpServletResponse response) {
        int styleId =Integer.parseInt(request.getParameter("styleId"));
        List<Product> productList=productService.findByStyleID(styleId);
        String email=request.getParameter("email");
        request.setAttribute("email",email);
        request.setAttribute("listProduct",productList);
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/productList.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action = "";
        }
        switch (action){
            case "":
            default:

        }
    }
}
