package sda.pl.repository;

import org.hibernate.Session;
import org.hibernate.query.Query;
import sda.pl.domain.Color;
import sda.pl.HibernateUtil;
import sda.pl.domain.Product;
import sda.pl.domain.ProductType;
import sda.pl.domain.WareHauseName;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductRepository {


    public static boolean saveProduct(Product product){
        Session session = null;
        try {
            session = HibernateUtil.openSession();

            session.save(product);
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

    public static boolean saveOrUpdateProduct(Product product){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.saveOrUpdate(product);
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

    public static Optional<Product> findProduct(Long id){
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            Product product = session.find(Product.class, id);
            return Optional.ofNullable(product);
        } catch (Exception e) {
            e.printStackTrace();
            return  Optional.empty();
        } finally {
            if(session != null){
                session.close();
            }
        }

    }

    public static List<Product> findAll(){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT p FROM Product p ";
            Query query = session.createQuery(hql);
            List resultList = query.getResultList();

            return resultList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public static List<Product> findAllWithPriceNetLessThan(BigDecimal price){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT p FROM Product p WHERE p.price.priceNet < :price " +
                    "ORDER BY p.price.priceNet DESC";
            Query query = session.createQuery(hql);
            query.setParameter("price", price);
            query.setMaxResults(100);
            List resultList = query.getResultList();
            return resultList;

        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    public static Long countAll(){
        Session session = null;

        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT COUNT(p) FROM Product p ";
            Query query = session.createQuery(hql);
            Long singleResult = (Long) query.getSingleResult();
            return singleResult;

        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }

    }

    public static List<Product> findByNameCriteriaQuery(String name){
        Session session = null;
        try {
            session=HibernateUtil.openSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Product> query = cb.createQuery(Product.class);
            Root<Product> from = query.from(Product.class);
            CriteriaQuery<Product> select = query.select(from);
//            query.where(cb.like(from.get("name"),"%"+name+"%"));
            Predicate whereNameLike = cb.like(from.get("name"),"%"+name+"%");
//            Predicate whiteProducct = cb.equal(from.get("color"), Color.WHITE);

//            Predicate whereNameLikeAndColorWhite = cb.and(whereNameLike,whiteProducct);
//            Predicate whereNameLike = cb.and(whereNameLike);
            query.where(whereNameLike);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if(session != null && session.isOpen()){
                session.close();
            }
        }
    }

    public static boolean findProductWithMagic(Long id){
        //dirty checking
        //przez to że pobraliśmy encję i w Transaction, oraz zrobimy commita
        //to hibernate widzi commita i zmieni
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.getTransaction().begin();
            Product product = session.find(Product.class, id);
//            product.setName(product.getName()+" ++");
            product.addStock(WareHauseName.WEB,new BigDecimal(25));
//            product.getStockSet().clear();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null && session.isOpen() && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            return false;
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static List<Product> findAllByCategory(ProductType productType) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT p FROM Product p where p.productType= :type";
            Query query = session.createQuery(hql);
            query.setParameter("type", productType);
            List resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static List<Product> findAllByName(String name) {
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            String hql = "SELECT p FROM Product p where p.name LIKE :name";
            Query query = session.createQuery(hql);
            query.setParameter("name", "%"+name+"%");
            List resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
