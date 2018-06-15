package sda.pl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.pl.domain.*;
import sda.pl.repository.*;
import sun.security.ssl.HandshakeInStream;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {

//        for (int i = 0; i < 10; i++) {
//            Product sok = Product .builder()
//                    .name("Sok "+i)
//                    .color(Color.RED)
//                    .price(Price.builder()
//                            .priceGross(new BigDecimal(1.23*i))
//                            .priceNet(new BigDecimal(1.0*i))
//                            .priceSymbol("PLN").build()
//                    ).build();
//            ProductRepository.saveProduct(sok);
//        }

//        Product maslo = Product .builder()
//                .name("Maslo Drogie")
//                .color(Color.WHITE)
//                .price(Price.builder()
//                        .priceGross(new BigDecimal("10.50"))
//                        .priceNet(new BigDecimal("7.25"))
//                        .priceSymbol("PLN").build()
//                        ).build();
//        ProductRepository.saveProduct(maslo);

//        Optional<Product> product1 = ProductRepository.findProduct(1L);
//        System.out.println(product1.toString());

//        ProductRepository.findAll().forEach(p-> System.out.println(p.getId() + " " + p.getName() + " cena: " + p.getPrice().priceGross ));

//        ProductRepository.findAllWithPriceNetLessThan(new BigDecimal(5.2)).forEach(p-> System.out.println(p.getId() + " " + p.getName() + " cena: " + p.getPrice().priceGross ));

//        System.out.println(ProductRepository.countAll());

//        Optional<Product> product = ProductRepository.findProduct(2L);
//        if (product.isPresent()) {
//            Product product2 = product.get();
//            product2.setName("Jogurt");
//            product2.setPrice(Price.builder()
//                                        .priceGross(new BigDecimal("8.50"))
//                                        .priceNet(new BigDecimal("6.25"))
//                                        .priceSymbol("PLN").build());
//            ProductRepository.saveOrUpdateProduct(product2);
//        }


//        Optional<Product> product = ProductRepository.findProduct(2L);
//        if (product.isPresent()) {
//            Product product2=product.get();
//            Order order = Order.builder()
//                    .date(LocalDate.now())
//                    .rodo(true)
//                    .cityName("Poznań")
//                    .build();
//            OrderDetail detail = OrderDetail.builder()
//                    .amount(5L)
//                    .product(product2)
//                    .price(product2.getPrice())
//                    .build();
//
//            order.addOrderDetail(detail);
//
//            OrderRepository.saveOrder(order);
//        }


//      User user1 = User.builder()
//                .email("testowyMail")
//                .password("test")
//                .cityName("miasto")
//                .firstName("Jan")
//                .lastName("Nowak")
//                .street("ulica")
//                .build();
//
//        UserRepository.saveOrUpdate(user1);
//
//        User user2 =  UserRepository.findByEmailAndPassword("testowyMail","test").get();
//        User user3 =  UserRepository.findUser(1L).get();

//        Stock stock = Stock.builder()
//                .amount(new BigDecimal(200))
//                .product(ProductRepository.findProduct(9L).get())
//                .wareHauseName(WareHauseName.MAIN)
//                .build();


//        User user =  UserRepository.findUser(1L).get();
//        Optional<Cart> cartO = CartRepository.findCart(1L);
//        Cart cart = cartO.get();
//
//        Order newOrder = cart.createNewOrder();
//
//        OrderRepository.saveOrder(newOrder);


//        Optional<Product> product1 = ProductRepository.findProduct(1L);
//        User user =  UserRepository.findUser(1L).get();
//        ProductRating productRating = ProductRating.builder()
//                .product(product1.get())
//                .description("opis test")
//                .isActive(true)
//                .rate(5)
//                .user(user)
//                .build();

//        ProductRatingRepository.saveOrUpdate(productRating);

//        List<ProductRating> allActiveByProductId = ProductRatingRepository.findAllActiveByProductId(1L);
//        Optional<ProductRating> productRating1 = ProductRatingRepository.findProductRating(1L);

//     advertisingBanners.forEach(a-> System.out.println(a.getId()));
//        List<AdvertisingBanner> all = AdvertisingBannerRepository.findAll();
//        all.forEach(a -> System.out.println(a.getId()));

        Optional<Product> product1 = ProductRepository.findProduct(1L);
        User user =  UserRepository.findUser(1L).get();
        ProductRating productRating = ProductRating.builder()
                .product(product1.get())
                .description("opis test z validatorem poza zakresie")
                .isActive(true)
                .rate(8)
                .user(user)
                .build();

        ProductRatingRepository.saveOrUpdate(productRating);
    }
}
