package com.k10.repo;

import com.k10.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class CustomerRepo {


    class CustomerMapper implements RowMapper<Customer> {

        @Override
        public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Customer(resultSet.getInt(0), resultSet.getString(1), resultSet.getString(2));
        }
    }

    @Autowired
    private DataSource dataSource;

    public Customer getCustomer(Integer id) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.queryForObject("Select * from j2eedb.customer where id =?", new Object[]{id}, new CustomerMapper());
    }

    public List<Customer> getCustomers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("Select * from j2eedb.customer ", new CustomerMapper());
    }

    public void saveCustomer(Customer customer) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        String sql = "INSERT INTO j2eedb.customer " + "(id, name, email) VALUES (?, ?, ?)";

        jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.update(sql, new Object[]{customer.getId(), customer.getName(), customer.getEmail()});
    }

}
