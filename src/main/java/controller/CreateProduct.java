package controller;

import model.Product;
import model.Style;
import model.Type;
import service.Type.ITypeService;
import service.Type.TypeService;
import service.product.IProduct;
import service.product.ProductService;
import service.style.IStyleService;
import service.style.StyleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Create", urlPatterns = {"/create"})
public class CreateProduct extends HttpServlet {
    IProduct productService = new ProductService();
    ITypeService typeService = new TypeService();
    IStyleService styleService = new StyleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       createProduct(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        createProduct(req, resp);
    }

    public void createProduct(HttpServletRequest request, HttpServletResponse response){
        String name = request.getParameter("name");
        int typeID = Integer.parseInt(request.getParameter("type"));
        Type type = typeService.findTypeById(typeID);
        int styleID = Integer.parseInt(request.getParameter("style"));
        Style style = styleService.findByID(styleID);
        String imageURL = request.getParameter("image");
        double price = Double.parseDouble(request.getParameter("price"));
        Product product = new Product(name, type, style, price, imageURL);
        String[] sizes = request.getParameterValues("size");
        int[] sizeList = new int[sizes.length];
        for (int i = 0; i < sizes.length; i++) {
            sizeList[i] = Integer.parseInt(sizes[i]);
        }
        productService.save(product, sizeList);
        try {
            response.sendRedirect("/management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
