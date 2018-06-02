package sda.pl.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.pl.HibernateUtil;
import sda.pl.domain.ProductRating;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductRatingRepository {

    public static boolean saveOrUpdate(ProductRating productRating){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(productRating);
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

    public static Optional<ProductRating> findProductRating(Long id){
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            ProductRating productRating = session.find(ProductRating.class, id);
            return Optional.ofNullable(productRating);
        } catch (Exception e) {
            e.printStackTrace();
            return  Optional.empty();
        } finally {
            if(session != null){
                session.close();
            }
        }

    }

    public static List<ProductRating> findAllActiveByProductId(Long productId){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
//            String hql = "SELECT pr FROM ProductRating pr  WHERE pr.product.id =:productId AND pr.isActive=true " +
//                    "order by pr.id desc ";
            String hql = "SELECT pr FROM ProductRating pr  WHERE pr.product.id =:productId AND (pr.isActive=true OR pr.rate >4 ) " +
                    "order by pr.id desc ";
            Query query = session.createQuery(hql);
            query.setParameter("productId",productId);
            List resultList = query.getResultList();
            return resultList;

        } catch (Exception e) {
            e.printStackTrace();return Collections.emptyList();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

}
