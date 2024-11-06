package app;

import domain.Person;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
        EntityManager em = emf.createEntityManager();

        //Testing hibernate persistence
        Person p1 = new Person(null, "Carlos da Silva", "calos@gmail.com");
        Person p2 = new Person(null, "Joaquim Torres", "joaguim@gmail.com");
        Person p3 = new Person(null, "Ana Maria", "ana@gmail.com");

        //JPA needs a transaction when it's not reading from db
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.persist(p3);
        em.getTransaction().commit();

        System.out.println("Entities persisted!");

        //Testing hibernate find
        Person personToFind = em.find(Person.class, 1);
        System.out.println("Person: " + personToFind);

        //Testing hibernate delete
        //The entity to be deleted has to be monitored by JPA (i.e. recovered from db [find])
        em.getTransaction().begin();
        Person personToDelete = em.find(Person.class, 2);
        em.remove(personToDelete);
        em.getTransaction().commit();
        System.out.println("Person with id: 2 deleted!");

        em.close();
        emf.close();
    }
}
