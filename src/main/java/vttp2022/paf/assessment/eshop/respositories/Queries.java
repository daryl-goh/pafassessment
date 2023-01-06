package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    public static final String SQL_FIND_CUSTOMER_BY_NAME = "select name, address, email from customers where name = ?";
    public static final String SQL_SAVE_ORDER_TO_ESTORE = "insert into orders (order_id, delivery_id, name, address, email, status) values (?, ?, ?, ?, ?, ?)";
    public static final String SQL_INSERT_INTO_ORDER_STATUS = "insert into order_status (order_id, delivery_id, status) values (?, ?, ?)";
    public static final String SQL_JOIN_ORDERS_AND_ORDER_STATUS = "select eshop.order_status.status, estore.orders.name from eshop.order_status join estore.orders on eshop.order_status.order_id = estore.orders.order_id where estore.orders.name = ?";
}
