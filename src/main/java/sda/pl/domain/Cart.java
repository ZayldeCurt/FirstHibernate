package sda.pl.domain;

import lombok.*;
import sda.pl.core.ShopException;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @OneToOne
    @JoinColumn
    User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<CartDetail> cartDetailSet;

    @Transient
    boolean valid;

    public void addProductToCart(Product product, Long amount){
        if(cartDetailSet == null){
            cartDetailSet = new HashSet<>();
        }

        Optional<CartDetail> first = cartDetailSet.stream().filter(cd -> cd.getProduct().getId().equals(product.getId())).findFirst();
        long sum = product.getSumStockForSale();
        if(!first.isPresent()){
            if(amount>sum){
                amount=sum;
            }
            CartDetail newCartDetail = CartDetail.builder()
                    .amount(amount)
                    .price(product.getPrice())
                    .cart(this)
                    .product(product)
                    .build();
            cartDetailSet.add(newCartDetail);
        }else{
//            first.ifPresent(cd->cd.setAmount(cd.getAmount()+amount));
            CartDetail cd = first.get();
            if(cd.getAmount()+amount>sum){
                amount=sum-cd.getAmount();
            }
            cd.setAmount(cd.getAmount()+amount);
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
        long sum = product.getSumStockForSale();

        Optional<CartDetail> productInCart = cartDetailSet.stream()
                .filter(cd -> cd.getProduct().getId().equals(product.getId())).findFirst();
        if (productInCart.isPresent()) {
            CartDetail cartDetail = productInCart.get();
            if(newAmount>sum){
                cartDetail.setAmount(sum);
            }else{
                cartDetail.setAmount(newAmount);
            }
        }else{
            System.out.println("Warning: Product doesn't exist");
        }
    }

    public void checkIsValid(){
        setValid(true);
        getCartDetailSet().forEach(cd->{
            long sumStockForSale = cd.getProduct().getSumStockForSale();
            if(sumStockForSale<cd.getAmount()){
               setValid(false);
            }
        });
    }

    public Order createNewOrder() throws ShopException{
        checkIsValid();
        if(!valid){
            throw new ShopException("Brak części produktów");
        }
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
