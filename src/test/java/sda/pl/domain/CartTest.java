package sda.pl.domain;

import org.hibernate.Session;
import org.junit.Test;
import sda.pl.HibernateUtil;
import sda.pl.core.ShopException;
import sda.pl.repository.CartRepository;
import sda.pl.repository.OrderRepository;
import sda.pl.repository.ProductRepository;
import sda.pl.repository.UserRepository;

import java.util.Optional;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void createNewOrder() {
        //podejscie prowadzacego
//        Cart cart = new Cart();
//        UserRepository.findUser(1L).ifPresent(u->cart.setUser(u));
//        ProductRepository.findProduct(10L).ifPresent(p->cart.addProductToCart(p,5L));
//        Order newOrder = cart.createNewOrder();
//        OrderRepository.saveOrder(newOrder);


//        //moje podejscie
//        User user =  UserRepository.findUser(3L).get();
//        Optional<Cart> cartO = CartRepository.findCart(2L);
//        Cart cart = cartO.get();
//
//        Order newOrder = cart.createNewOrder();
//
//        OrderRepository.saveOrder(newOrder);

        Cart cart = new Cart();
        UserRepository.findUser(2L).ifPresent(u->cart.setUser(u));
        ProductRepository.findProduct(1L).ifPresent(p->cart.addProductToCart(p,2L));
        ProductRepository.findProduct(14L).ifPresent(p->cart.addProductToCart(p,100L));
        Order newOrder = null;
        try {
            newOrder = cart.createNewOrder();
        } catch (ShopException e) {
            e.printStackTrace();
        }
        OrderRepository.saveOrder(newOrder);
    }
}