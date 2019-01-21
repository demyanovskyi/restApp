package com.demyanovsky.repository;

import com.demyanovsky.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class OrderRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;

    public void save(Order order) {
        String sql = "INSERT INTO \"order\" (id, user_id) VALUES(?, ?)";
        String sql1 = "INSERT INTO public.order_product (order_id, product_id[]) VALUES(?, ?);";
        jdbcTemplate.update(sql, new Object[]{order.getId(), order.getUserId()});
        List<UUID> tmp = order.getListProductID();
        for (UUID id : tmp) {
            jdbcTemplate.update(sql1, new Object[]{order.getId(), id});
        }
        List<UUID> orders = order.getListProductID();

    }

    public List<Order> getAll() {

        final String sql = "SELECT id, user_id FROM \"order\"";
        List<Order> orderList = jdbcTemplate.query(sql, new OrderMapper());
        return orderList;

    }

    public Order getOrder(UUID orderID) {
        String sql1 = "SELECT user_id FROM public.\"order\" where \"order\".id = " + "'" + orderID + "'" + " ;";
        List<InnerProducts> orderList = getProductsIdFromORder(orderID);
        List<UUID> userID = jdbcTemplate.query(sql1, (resultSet, rwNumber) -> {
            UUID userID1;
            String tm = resultSet.getString("user_id");
            UUID id = UUID.fromString(tm);
            userID1 = id;

            return userID1;
        });
        Order tempOrder = new Order();
        List tmp = new ArrayList();
        for (InnerProducts iterator : orderList) {
            tmp.add(iterator.getId());
        }
        tempOrder.setListProductID(tmp);
        tempOrder.setId(orderID);
        tempOrder.setUserId(userID.get(0));
        return tempOrder;
    }


    public List<InnerProducts> getProductsIdFromORder(UUID orderID) {
        String sql = "SELECT * FROM \"order\" join order_product on \"order\".id = order_product.order_id " +
                " where \"order\".id = " + "'" + orderID + "'" + " ;";

        List<InnerProducts> productList = jdbcTemplate.query(sql, new UUIDMapper());


        return productList;
    }

    class UUIDMapper implements RowMapper<InnerProducts> {
        public InnerProducts mapRow(ResultSet resultSet, int rowNum) throws SQLException {

            InnerProducts innerProduct = new InnerProducts();
            String tm = resultSet.getString("product_id");
            UUID id = UUID.fromString(tm);
            innerProduct.setId(id);

            return innerProduct;
        }

    }

    public class InnerProducts {
        UUID id;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }
    }

}
