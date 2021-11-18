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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManagerControl", urlPatterns = {"/management"})
public class ManageServlet extends HttpServlet {
    IProduct productService = new ProductService();
    ISize sizeService = new SizeService();
    ITypeService typeService = new TypeService();
    IStyleService styleService = new StyleServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        manage(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        manage(req, resp);
    }
    private void manage(HttpServletRequest request, HttpServletResponse response){
        List<Product> productList = productService.findAll();
        request.setAttribute("list", productList);
        List<Size> sizeList = sizeService.findAllSize();
        request.setAttribute("size", sizeList);
        List<Type> typeList = typeService.findAllType();
        request.setAttribute("type", typeList);
        List<Style> styles = styleService.showAll();
        request.setAttribute("style", styles);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Manage.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

