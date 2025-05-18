package com.example.compare.services;

import com.example.compare.vertica.model.Inventory;
import com.example.compare.vertica.model.InventoryDao;
import com.example.compare.vertica.model.InventoryMapper;
import com.example.compare.vertica.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {
/*    @Autowired
    VendorRepository vendorRepository;*/

    @Qualifier("VerticaTemplate")
    @Autowired
    JdbcTemplate jdbcTemplate;

    @PersistenceUnit(unitName = "vertica")
    private EntityManagerFactory verticaEntityManagerFactory;

    public void createVendor() {
        var entityManager = verticaEntityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            for (int i = 100; i <= 100000; i++) {
                //long i = 100001;
                long rn = Math.round(Math.random() * 100000);
                Vendor vendor = new Vendor(i, "" + rn, "Address", "Lahore", "PU", "North", 2, Instant.now());
                entityManager.persist(vendor);
                if (i % 10000 == 0) {
                    System.out.println(Calendar.getInstance().toInstant() + " : " +
                            i + " rows inserted");
                    entityManager.getTransaction().commit();
                    entityManager.getTransaction().begin();
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        } finally {
           /* entityManager.flush();
            entityManager.clear();*/
            entityManager.getTransaction().commit();
            entityManager.close();
        }
    }

    public List<InventoryDao> getInventory() {
        List<InventoryDao> vendors = new ArrayList<>();

        var entityManager = verticaEntityManagerFactory.createEntityManager();
        try{
            Query query =entityManager.createNativeQuery("select * from inventory_fact limit 200000", Inventory.class);

            vendors= (List<InventoryDao>) query.getResultList().stream().map
                    (o -> new InventoryDao(((Inventory)o))).collect(Collectors.toList());

        }finally

        {

            entityManager.close();
        }
        return vendors;
    }

    public List<InventoryDao> getInventoryJdbc(){
        PreparedStatementCreator statementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("SELECT date_key, product_key, product_version, warehouse_key, qty_in_stock, inventory_date\n" +
                        "FROM public.inventory_fact limit 200000");
                //ps.setInt(1, 1);
                return ps;
            }
        };
        return jdbcTemplate.query(statementCreator, new InventoryMapper());
        //return jdbcTemplate.query("select first_name , last_name , email_address from customer ", new CustomerMapper());
    }

}

