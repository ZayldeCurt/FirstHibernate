package sda.pl.web;

import sda.pl.domain.Cart;
import sda.pl.domain.Price;
import sda.pl.domain.Product;
import sda.pl.repository.CartRepository;
import sda.pl.repository.ProductRepository;
import sda.pl.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AddProductToCartServlet",urlPatterns = "/AddProductToCart")
public class AddProductToCartServlet extends HttpServlet {

    public static final Long USER_ID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long productAmount = Long.parseLong(request.getParameter("productAmount"));
        long productId = Long.parseLong(request.getParameter("productId"));

        Cart cart = null;
        Optional<Cart> cartByUserID = CartRepository.findCartByUserID(USER_ID);
        if(cartByUserID.isPresent()){
            cart = cartByUserID.get();
        }
        else {
            cart = new Cart();
            cart.setUser(UserRepository.findUser(USER_ID).get());
        }

        Optional<Product> product = ProductRepository.findProduct(productId);
        if(product.isPresent()) {
            cart.addProductToCart(product.get(), productAmount);
            CartRepository.saveOrUpdateCart(cart);
        }

        PrintWriter writer = response.getWriter();
        writer.write("productAmount "+ productAmount);
    }

}
