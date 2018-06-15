package sda.pl.web;

import sda.pl.domain.Product;
import sda.pl.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "AllProductListServlet", urlPatterns = "/AllProductList")
public class AllProductListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> all = ProductRepository.findAll();


        PrintWriter writer = response.getWriter();
        writer.write("<html><head></head><body>");
        writer.write("<table><tr><td>Id</td><td>Nazwa</td><td>Kolor</td><td>Ilosc</td></tr>");
        for(Product p : all){
            writer.write("<tr><td>"+p.getId()+"</td><td>"+p.getName()+"</td><td>"+p.getColor()+"</td><td>"+p.getSumStockForSale()+"</td></tr>");
//            writer.write("<tr><td>"+p.getId()+"</td><td>"+p.getName()+"</td><td>"+p.getColor()+"</td></tr>");
        }
        writer.write("</table>");
        writer.write("</body></html>");
    }
}
