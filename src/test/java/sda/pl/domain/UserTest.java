package sda.pl.domain;

import org.junit.Test;
import sda.pl.repository.ProductRatingRepository;
import sda.pl.repository.ProductRepository;
import sda.pl.repository.UserRepository;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void rateProduct() {

        Optional<User> user = UserRepository.findUser(3L);

        user.ifPresent(u-> {
            ProductRating productRating = u.rateProduct(1,"kupa", ProductRepository.findProduct(1L).get());
            ProductRatingRepository.saveOrUpdate(productRating);
        });

    }
}