package sda.pl.web;

import sda.pl.domain.Color;
import sda.pl.domain.Price;
import sda.pl.domain.Product;
import sda.pl.repository.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HelloWorldServlet",urlPatterns = "/hello")
public class HelloWorldServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");

        List<Product> all = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("Maslo 1");
        product1.setColor(Color.WHITE);
        Product product2 = new Product();
        product2.setName("Maslo 2");
        product2.setColor(Color.RED);
        all.add(product1);
        all.add(product2);

        PrintWriter writer = response.getWriter();
        writer.write("<html>" );
            writer.write("<head></head>" );
            writer.write("<body>");
                writer.write("<h1>Hello "+firstName+ " ! </h1>");
                writer.write("<table border=1px>");
                    writer.write("<thead>" +
                                            "<tr>" +
                                                "<th>Nazwa</th>" +
                                                "<th>Cena </th>" +
                                            "</tr>" +
                                        "</thead>");
                    writer.write("<tbody>");

                    for(Product p : all){
                        writer.write("<tr>");
                            writer.write("<td>"+p.getName()+"</td>");
                            writer.write("<td>"+p.getColor()+"</td>");
                        writer.write("<tr>");
                    }

                    writer.write("</tbody>");
                writer.write("</table>");
            writer.write("</body>");
        writer.write("</html>");
    }
}
