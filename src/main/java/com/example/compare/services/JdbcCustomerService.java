package com.example.compare.services;

import com.example.compare.pg.model.CustomerDao;
import com.example.compare.pg.model.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

@Service
public class JdbcCustomerService {

    @Qualifier("CustomerTemplate")
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void createCustomersJdbc(long start,long limit){


        for (long i = start; i <= limit; i+=10000) {

            batchInsertJdbc(i);


        }

    }

    @Transactional
    public void batchInsertJdbc(long step){

        for(long i=step; i>(step-10000); i-- ) {
            String customerSql = "INSERT INTO public.customer " +
                    "(id, first_name, last_name, email_address) " +
                    "VALUES ";

            String addressSql = "INSERT INTO public.address " +
                    "(id, customer_id, street, city, country) " +
                    "VALUES ";

            long rn = Math.round(Math.random() * 100000);

            jdbcTemplate.execute(customerSql + "(" + i + ", '" + rn + "','" + "last" + rn + "','" + rn + "@mail.com')");
            jdbcTemplate.execute(addressSql + "(" + i + "," + i + ",'27 Broadway','new York','United states')");

            if (i % 10000 == 0)
                System.out.println(Calendar.getInstance().toInstant() + " : " +
                        i + " rows inserted");
        }

    }

    public List<CustomerDao> getCustomersJdbc(){
        PreparedStatementCreator statementCreator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement("select first_name , last_name , " +
                        " email_address from customer where id>?");
                ps.setInt(1, 1);
                return ps;
            }
        };
            jdbcTemplate.setFetchSize(1000);

        return jdbcTemplate.query(statementCreator, new CustomerMapper());
        //return jdbcTemplate.query("select first_name , last_name , email_address from customer ", new CustomerMapper());
    }

    public void deleteData(){

        String customerSql="DELETE FROM public.customer " ;

        String addressSql="DELETE FROM public.address" ;

        jdbcTemplate.execute(addressSql);

        jdbcTemplate.execute(customerSql );



    }
}
