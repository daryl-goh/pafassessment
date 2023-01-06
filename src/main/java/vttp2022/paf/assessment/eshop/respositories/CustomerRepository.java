package vttp2022.paf.assessment.eshop.respositories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.CustomerRowMapper;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;
@Repository
public class CustomerRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	// You cannot change the method's signature
	public List<Customer> findCustomerByName(String cname) {
		// TODO: Task 3 
		
		List<Customer> c = jdbcTemplate.query(SQL_FIND_CUSTOMER_BY_NAME, new CustomerRowMapper(), new Object[] {cname});
		return c;
	}
}
