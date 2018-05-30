package sda.pl.domain;

import lombok.*;
import org.hibernate.Session;
import sda.pl.Product;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "cartDetailSet")
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn
    User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    Set<CartDetail> cartDetailSet;

    public void addProductToCart(Product product, Long amount){
        if(cartDetailSet == null){
            cartDetailSet = new HashSet<>();
        }

        Optional<CartDetail> first = cartDetailSet.stream().filter(cd -> cd.getProduct().getId().equals(product.getId())).findFirst();
        if(!first.isPresent()){
            CartDetail newCartDetail = CartDetail.builder()
                    .amount(amount)
                    .price(product.getPrice())
                    .cart(this)
                    .product(product)
                    .build();
            cartDetailSet.add(newCartDetail);
        }else{
            first.ifPresent(cd->cd.setAmount(cd.getAmount()+amount));
        }
    }

    public void substractProductInCart(Product product){
        if(cartDetailSet == null){
            return;
        }
        Optional<CartDetail> productInCart = cartDetailSet.stream().filter(cd -> cd.getProduct().getId().equals(product.getId())).findFirst();
        productInCart.ifPresent(cd->{
            if(cd.getAmount()>0){
                cd.setAmount(cd.getAmount()-1);
            }else{
                cd.setAmount(0L);
            }
        });
    }

    public void changeProductAmount(Product product, Long newAmount){
        if(newAmount<0L) newAmount=0L;
        Optional<CartDetail> productInCart = cartDetailSet.stream()
                .filter(cd -> cd.getProduct().getId().equals(product.getId())).findFirst();
        if (productInCart.isPresent()) {
            CartDetail cartDetail = productInCart.get();
            cartDetail.setAmount(newAmount);
        }else{
            System.out.println("Warning: Product doesn't exist");
        }
    }

    public Order createNewOrder(){
        Order order = Order.builder()
                .cityName(this.getUser().cityName)
                .date(LocalDateTime.now())
                .user(user)
                .RODO(true)
                .build();

        this.cartDetailSet.forEach(cd->{
            order.addOrderDetail(new OrderDetail(cd));
        });
        order.calculateTotalPrice();
        return order;
    }
}
