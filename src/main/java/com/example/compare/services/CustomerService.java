package com.example.compare.services;

import com.example.compare.pg.model.Address;
import com.example.compare.pg.model.Customer;
import com.example.compare.pg.model.CustomerDao;
import com.example.compare.pg.repository.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
//logging

public class CustomerService {
    //logging
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    CustomerRepository customerRepository;

/*    @Qualifier("MainTemplate")
    @Autowired
    private JdbcTemplate jdbcTemplate;*/


 /*   @PersistenceContext(unitName = "customer")
    private EntityManager entityManager;*/

    @PersistenceUnit(unitName = "customer")
    private EntityManagerFactory customerEntityManagerFactory;

    @Transactional
    public void createCustomerJpa(long start, long limit) {


        System.out.println(Calendar.getInstance().toInstant());
        for (long i = start; i <= limit; i++) {

            long rn = Math.round(Math.random() * 100000);
            Customer dave = new Customer(i, "" + rn, "last" + rn);

            dave.setEmailAddress(rn + "@mail.com");
            dave.add(new Address(i, "27 Broadway", "New York", "United States"));

            Customer result = customerRepository.save(dave);
            //assertThat(result.getId(), is(notNullValue()));

            if (i % 10000 == 0)
                System.out.println(Calendar.getInstance().toInstant() + " : " +
                        i + " rows inserted");

        }
        System.out.println(Calendar.getInstance().toInstant());
    }


    public List<CustomerDao> getCustomers() {

        List<Customer> customers= customerRepository.findAll();
        return customers.stream().map(CustomerDao::new).collect(Collectors.toList());
    }


/*    @Transactional*/
    public void createCustomerEntity(long start, long limit) {
        var entityManager = customerEntityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        try {
             System.out.println(Calendar.getInstance().toInstant());

            for (long i = start; i <= limit; i++) {
                //long i = 100001;
                long rn = Math.round(Math.random() * 100000);
                Customer dave = new Customer(i, "" + rn, "last" + rn);

                dave.setEmailAddress(rn + "@mail.com");
                dave.add(new Address(i, "27 Broadway", "New York", "United States"));

                //entityManager.persist();
                entityManager.persist(dave);

                //Customer result = customerRepository.save(dave);
                //assertThat(result.getId(), is(notNullValue()));
                //  entityManager.getTransaction().commit();
                if (i % 10000 == 0) {
                    System.out.println(Calendar.getInstance().toInstant() + " : " +
                            i + " rows inserted");
                    entityManager.getTransaction().commit();
                    entityManager.getTransaction().begin();
                }
            }
            System.out.println(Calendar.getInstance().toInstant());



    }finally

    {
        entityManager.flush();
        entityManager.clear();
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

    public List<CustomerDao>  getCustomersEntity() {
        List<CustomerDao> customers = new ArrayList<>();

        var entityManager = customerEntityManagerFactory.createEntityManager();
        try{
        Query query =entityManager.createNativeQuery("select * from customer",Customer.class);

        customers= (List<CustomerDao>) query.getResultList().stream().map
                (o -> new CustomerDao(((Customer)o))).collect(Collectors.toList());

        }finally

        {

            entityManager.close();
        }
        return customers;
    }

//@Transactional
    public void createCustomerBatch() {

        ArrayList<Customer> customers = new ArrayList<>();
        System.out.println(Calendar.getInstance().toInstant());
        for (long i = 1; i <= 100000; i++) {

            long rn = Math.round(Math.random() * 100000);
            Customer customer = new Customer(i, "" + rn, "last" + rn);

            customer.setEmailAddress(rn + "@mail.com");
            customer.add(new Address(i, "27 Broadway", "New York", "United States"));

            customers.add(customer);
            //Customer result = customerRepository.save(dave);

            //assertThat(result.getId(), is(notNullValue()));

          /*  if (i % 10000 == 0)
                System.out.println(Calendar.getInstance().toInstant() + " : " +
                        i + " rows inserted");*/


        }
    customerRepository.saveAll(customers);
        System.out.println(Calendar.getInstance().toInstant());
    }
}
