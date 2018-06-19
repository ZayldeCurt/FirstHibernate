package sda.pl.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"orderDetailSet","productImage","cartDetailSet","productRatingSet","stockSet"})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    @Embedded
    Price price;
    @Enumerated(EnumType.STRING)
    Color color;

    @Enumerated(EnumType.STRING)
    ProductType productType;

    @OneToMany(mappedBy = "product")
    Set<OrderDetail> orderDetailSet;

    @OneToOne(mappedBy = "product")
    ProductImage productImage;

    @OneToMany(mappedBy = "product")
    Set<CartDetail> cartDetailSet;

    @OneToMany(mappedBy = "product")
    Set<ProductRating> productRatingSet;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    Set<Stock> stockSet;

    public void addStock(WareHauseName name, BigDecimal amount){
        if(stockSet==null){
            stockSet=new HashSet<>();
        }
        Optional<Stock> stockExist = stockSet.stream().filter(s -> s.getWareHauseName().equals(name)).findFirst();
        if(!stockExist.isPresent()) {
            Stock stock = Stock.builder()
                    .wareHauseName(name)
                    .product(this)
                    .amount(amount)
                    .build();
            stockSet.add(stock);
        }else{
            stockExist.ifPresent(s->{
                s.setAmount(s.getAmount().add(amount));
            });
        }
    }

    public long getSumStockForSale() {
        return getStockSet().stream().filter(stock -> !stock.getWareHauseName().equals(WareHauseName.COMPLAINT))
                .mapToLong(s -> s.getAmount().longValue()).sum();
    }

    public void addProductRating(ProductRating productRating){
        if(productRatingSet == null){
            productRatingSet = new HashSet<>();
        }
        productRating.setProduct(this);
        productRatingSet.add(productRating);
    }
}
