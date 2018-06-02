package sda.pl;

import org.junit.Test;
import sda.pl.domain.Product;
import sda.pl.domain.WareHauseName;
import sda.pl.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductTest {

    @Test
    public void addStock() {
        //Given
        Optional<Product> product = ProductRepository.findProduct(2L);
//        Stock stock
        //When
        product.ifPresent(p->{
            p.addStock(WareHauseName.MAIN, BigDecimal.ONE);
            ProductRepository.saveOrUpdateProduct(p);
        });


    }
}