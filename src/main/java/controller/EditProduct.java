package controller;

import model.Product;
import model.Size;
import model.Style;
import model.Type;
import service.Type.ITypeService;
import service.Type.TypeService;
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

@WebServlet(name = "EditProduct", value = "/edit")
public class EditProduct extends HttpServlet {
    IProduct productService = new ProductService();
    ISize sizeService = new SizeService();
    ITypeService typeService = new TypeService();
    IStyleService styleService = new StyleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        editForm(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        edit(request, response);
    }
    private void editForm(HttpServletRequest request, HttpServletResponse response){

        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        request.setAttribute("P", product);
        List<Size> sizeList = sizeService.findAllSize();
        request.setAttribute("size", sizeList);
        List<Type> typeList = typeService.findAllType();
        request.setAttribute("type", typeList);
        List<Style> styles = styleService.showAll();
        request.setAttribute("style", styles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  void edit(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getParameter("id"));
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
        productService.edit(id, product, sizeList);
        try {
            response.sendRedirect("management");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
