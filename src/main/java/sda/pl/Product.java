package sda.pl;

import lombok.*;
import sda.pl.domain.*;

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

    public void addStock(User.WareHauseName name, BigDecimal amount){
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

}
