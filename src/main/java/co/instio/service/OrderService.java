package co.instio.service;

import co.instio.dto.OrdersView;
import co.instio.entity.Orders;

import java.util.List;

public interface OrderService {

    List<OrdersView> createOrders(List<Orders> orders);
    List<OrdersView> getAllOrders();
    OrdersView getById(Long orderId);
    OrdersView updateOrders(Long orderId,Orders orders);
    void deleteOrders(Long orderId);
}
