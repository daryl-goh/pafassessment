package vttp2022.paf.assessment.eshop.respositories;

import static vttp2022.paf.assessment.eshop.respositories.Queries.*;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp2022.paf.assessment.eshop.models.CustomerRowMapper;
import vttp2022.paf.assessment.eshop.models.OrderStatus;

@Repository
public class OrderStatusRepository {
    @Autowired
	private JdbcTemplate jdbcTemplate;

	public OrderStatus saveOrder(final OrderStatus os) {
		KeyHolder keyholder = new GeneratedKeyHolder();
		jdbcTemplate.update(conn -> {
			PreparedStatement ps = conn.prepareStatement(SQL_INSERT_INTO_ORDER_STATUS,
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, os.getOrderId());
			ps.setString(2, os.getDeliveryId());
			ps.setString(3, os.getStatus());
			return ps;
		}, keyholder);
		BigInteger primaryKeyVal = (BigInteger) keyholder.getKey();
		os.setOrderId(primaryKeyVal.toString());
		return os;
		
	}

    public List<OrderStatus> getOrderStatus(String name) {
		
		final List<OrderStatus> os = new LinkedList<>();
        
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_JOIN_ORDERS_AND_ORDER_STATUS, name);
	
        while (rs.next()) {
            os.add(OrderStatus.create(rs));
        }

        return os;
	}
}
