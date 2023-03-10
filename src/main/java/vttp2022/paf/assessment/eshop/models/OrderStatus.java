package vttp2022.paf.assessment.eshop.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

// DO NOT CHANGE THIS CLASS
public class OrderStatus {

	private String orderId;
	private String deliveryId = "";
	private String status = "pending"; // or "dispatched"

	public String getOrderId() { return this.orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	public String getDeliveryId() { return this.deliveryId; }
	public void setDeliveryId(String deliveryId) { this.deliveryId = deliveryId; }

	public String getStatus() { return this.status; }
	public void setStatus(String status) { this.status = status; }


	public static OrderStatus create(SqlRowSet rs) {
		OrderStatus os = new OrderStatus();
		os.setOrderId(rs.getString("order_id"));
		os.setDeliveryId(rs.getString("delivery_id"));
		os.setStatus(rs.getString("status"));
		return os;	
	}

	public JsonObject toJSON() {
        return Json.createObjectBuilder()
                .add("order_id", getOrderId())
                .add("delivery_id", getDeliveryId())
                .add("status", getStatus())
                .build();
    }

}
