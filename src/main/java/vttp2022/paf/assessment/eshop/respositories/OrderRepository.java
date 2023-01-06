package vttp2022.paf.assessment.eshop.respositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.Order;
import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
@Repository
public class OrderRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	// TODO: Task 3

	public Order saveOrder(final Order o) {
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(SQL_SAVE_ORDER_TO_ESTORE,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, o.getOrderId());
			ps.setString(2, o.getDeliveryId());
			ps.setString(3, o.getName());
			ps.setString(4, o.getAddress());
			ps.setString(5, o.getEmail());
			ps.setString(6, o.getStatus());
			return ps;
		}, keyholder);
		BigInteger primaryKeyVal = (BigInteger) keyholder.getKey();
		o.setOrderId(primaryKeyVal.toString());
		return o;
		
	}

	

}
