package main;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class DemoHib {

    public static List<Alien> getAliens(Session session) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Alien> cq = cb.createQuery(Alien.class);
        Root<Alien> rootEntry = cq.from(Alien.class);
        CriteriaQuery<Alien> all = cq.select(rootEntry);
        
        TypedQuery<Alien> allQuery = session.createQuery(all);
        return allQuery.getResultList();
    }

    public static void main(String[] args) {
//        Alien telusko = new Alien();
//        telusko.setAid(103);
//        telusko.setAname("Raina");
//        telusko.setColor("Red");

        List<Alien> aliens = new ArrayList();

        Configuration con = new Configuration().configure().addAnnotatedClass(Alien.class);

//        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
//        SessionFactory sf = con.buildSessionFactory(reg);
        SessionFactory sf = con.buildSessionFactory();

        Session session = sf.openSession();

        Transaction tx = session.beginTransaction();
        //        session.save(telusko);

        aliens = getAliens(session);

        tx.commit();

        for(Alien alien : aliens) {
            System.out.println(alien);
        }

    }
}
