package com.example.compare.controllers;

import com.example.compare.pg.model.CustomerDao;
import com.example.compare.services.CustomerService;
import com.example.compare.services.JdbcCustomerService;
import com.example.compare.services.QueryExecutorService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.*;
import java.util.stream.Collectors;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    JdbcCustomerService jdbcCustomerService;

    @Autowired
    QueryExecutorService queryExecutorService;


    @PostMapping("/create-customer-performance")
    public ResponseEntity<String> createCustomerPerformance(@RequestParam("start") long start,
                                                            @RequestParam("limit") long limit){
        long time =System.currentTimeMillis();
        customerService.createCustomerJpa(start,limit);
        System.out.println("Create customer JPA time : "+(System.currentTimeMillis() - time ));
        jdbcCustomerService.deleteData();

        time =System.currentTimeMillis();
        customerService.createCustomerEntity(start,limit);
        System.out.println("Create customer Entity time : "+(System.currentTimeMillis() - time ));
        jdbcCustomerService.deleteData();

        time =System.currentTimeMillis();
        jdbcCustomerService.createCustomersJdbc(start,limit);
        System.out.println("Create customer Jdbc time : "+(System.currentTimeMillis() - time ));

        jdbcCustomerService.deleteData();
        return ResponseEntity.ok("customer created");
    }

    @PostMapping("/get-customer-performance")
    public ResponseEntity<String> getCustomerPerformance(){
        long time =System.currentTimeMillis();
        customerService.getCustomers();
        System.out.println("Get customer JPA time : "+(System.currentTimeMillis() - time ));
       // jdbcCustomerService.deleteData();

        time =System.currentTimeMillis();
        customerService.getCustomersEntity();
        System.out.println("Get customer Entity time : "+(System.currentTimeMillis() - time ));
     //   jdbcCustomerService.deleteData();

        time =System.currentTimeMillis();
        jdbcCustomerService.getCustomersJdbc();
        System.out.println("Get customer Jdbc time : "+(System.currentTimeMillis() - time ));

       // jdbcCustomerService.deleteData();
        return ResponseEntity.ok("customer retreived");
    }


    @PostMapping("/create-customer")
    public ResponseEntity<String> createCustomer(@RequestParam("start") long start,@RequestParam("limit") long limit){
        System.out.println("Starting creation of customers : " + new Date());
        customerService.createCustomerJpa(start,limit);
        System.out.println("Ending creation of customers : "+ new Date());
        return ResponseEntity.ok("customer created");
    }
    @PostMapping("/create-customer-jdbc")
    public ResponseEntity<String> createCustomerJdbc(@RequestParam("start") long start,@RequestParam("limit") long limit){
        System.out.println("Starting creation of customers : " + new Date());
       // customerService.createCustomerJdbc();
        jdbcCustomerService.createCustomersJdbc(start,limit);
        System.out.println("Ending creation of customers : "+ new Date());

        return ResponseEntity.ok("customer created");
    }

    @PostMapping("/create-customer-batch")
    public ResponseEntity<String> createCustomerBatch(){
        System.out.println("Starting creation of customers : " + new Date());
        customerService.createCustomerBatch();
        System.out.println("Ending creation of customers : "+ new Date());
        return ResponseEntity.ok("customer created");
    }

    @PostMapping("/create-customer-entity")
    public ResponseEntity<String> createCustomerEntity(@RequestParam("start") long start,@RequestParam("limit") long limit){
        System.out.println("Starting creation of customers (entity): " + new Date());
        customerService.createCustomerEntity(start,limit);

        System.out.println("Ending creation of customers : "+ new Date());

        return ResponseEntity.ok("customer created");
    }

    @GetMapping("/get-customers-entity")
    public ResponseEntity<String> getCustomersEntity(){
        long start =System.currentTimeMillis();
        //System.out.println("Starting gettting of customers (entity): " + Calendar.getInstance().toInstant());
        List<CustomerDao> customers=customerService.getCustomersEntity();
        long end =System.currentTimeMillis();
        System.out.println("time taken "+ (end-start));
        //System.out.println("Ending getting of customers : "+ Calendar.getInstance().toInstant());

        return ResponseEntity.ok("customer retrieved"+customers.size());
    }

    @GetMapping("/get-customers-jpa")
    public ResponseEntity<String> getCustomersjpa(){
        long start =System.currentTimeMillis();
       // System.out.println("Starting gettting of customers (jpa): " + Calendar.getInstance().toInstant());
        List<CustomerDao> customers=customerService.getCustomers();

       // System.out.println("Ending getting of customers : "+ Calendar.getInstance().toInstant());
        long end =System.currentTimeMillis();
        System.out.println("time taken "+ (end-start));
        return ResponseEntity.ok("customer retrieved"+customers.size());
    }

    @GetMapping("/get-customers-jdbc")
    public ResponseEntity<List<CustomerDao>> getCustomersJdbc(){
        long start =System.currentTimeMillis();
        // System.out.println("Starting gettting of customers (jpa): " + Calendar.getInstance().toInstant());
        List<CustomerDao> customers=jdbcCustomerService.getCustomersJdbc();

        // System.out.println("Ending getting of customers : "+ Calendar.getInstance().toInstant());
        long end =System.currentTimeMillis();
        System.out.println("time taken "+ (end-start));

        return ResponseEntity.ok(customers.stream().limit(100).collect(Collectors.toList()));
    }

    @GetMapping("/get-dynamic")
    public ResponseEntity<JsonNode> getCustomersDynamic() {
        long start = System.currentTimeMillis();
        // System.out.println("Starting gettting of customers (jpa): " + Calendar.getInstance().toInstant());

        //v1
        //JsonNode customers = queryExecutorService.executeQuery("select first_name as firstName , last_name , " +
        //        " email_address from customer where id<:id",Collections.singletonMap("id", 1000));

        //v2
        JsonNode customers = queryExecutorService.executeQuery("select first_name as firstName , " +
                "last_name , reg_date, " +
                " email_address from customer where id in (:id)",Collections.singletonMap("id", List.of(1,2,3)));
      /*  Map<String, Object> params = new HashMap<>();
        params.put("id", List.of(1, 2, 3));
        params.put("firstName", "65127");

        JsonNode customers = queryExecutorService.executeQuery("select first_name as \"firstName\" , last_name , " +
                " email_address from customer where id in (:id)" +
                " and (:firstName is null  or first_name =:firstName )", params);*/



        // System.out.println("Ending getting of customers : "+ Calendar.getInstance().toInstant());
        long end = System.currentTimeMillis();
        System.out.println("time taken " + (end - start)+ "  "+ customers.size());
        return ResponseEntity.ok(customers);
    }

    @GetMapping(value = "/get-dynamic-query", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonNode> getCustomersDynamicQuery(@RequestParam ("query") String query,
                        @RequestParam ("offset") Integer offset, @RequestParam ("limit") Integer limit) {
        long start = System.currentTimeMillis();


        JsonNode customers = queryExecutorService.executeQuery(query +
                        (offset!=null ? (" offset "+offset) : "") +
                        (limit!=null ? (" limit "+limit) : ""),
                Collections.emptyMap());
        long end = System.currentTimeMillis();
        System.out.println("time taken " + (end - start)+ "milliseconds   "+ customers.size());
        return ResponseEntity.ok(customers);
    }
}
