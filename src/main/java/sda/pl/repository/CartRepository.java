package sda.pl.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.pl.HibernateUtil;
import sda.pl.domain.Cart;

import java.util.Optional;

public class CartRepository {

    public static boolean saveCart(Cart cart){
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            session.save(cart);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }

    }

    public static Optional<Cart> findCartByUserID(Long userId){
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT c FROM Cart c JOIN FETCH c.cartDetailSet WHERE c.user.id=:userId";
            Query query = session.createQuery(hql);
            query.setParameter("userId",userId);
            return Optional.ofNullable((Cart) query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        } finally {
            if(session != null){
                session.close();
            }
        }

    }

    public static boolean saveOrUpdateCart(Cart cart){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(cart);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if(session != null && session.isOpen() && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return false;
        }finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }

    }

    public static Optional<Cart> findCart(Long id){
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            Cart cart = session.find(Cart.class, id);
            cart.getCartDetailSet();
            return Optional.ofNullable(cart);
        } catch (Exception e) {
            e.printStackTrace();
            return  Optional.empty();
        } finally {
            if(session != null){
                session.close();
            }
        }

    }

    public static void deleteCart(Cart cart) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            String removeCartDetail = "DELETE FROM CartDetail cd WHERE cd.cart.id=:cartId";
            String removeCart = "DELETE FROM Cart c WHERE c.id = :cartId";

            Query query = session.createQuery(removeCartDetail);
            query.setParameter("cartId", cart.getId());
            query.executeUpdate();

            Query queryRemoveCart = session.createQuery(removeCart);
            queryRemoveCart.setParameter("cartId",cart.getId());
            queryRemoveCart.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            if(session!=null && session.isOpen() && session.getTransaction().isActive()){
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if(session !=null){
                session.close();
            }
        }
    }

    public static boolean deleteProductFromCart(Cart cart, Long productId) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();

            String hql = "delete from CartDetail cd where cd.product.id = :productId";
            Query query = session.createQuery(hql);
            query.setParameter("productId", productId);
            query.executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null && session.isOpen() && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
