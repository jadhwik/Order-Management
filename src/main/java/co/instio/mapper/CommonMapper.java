package co.instio.mapper;

import co.instio.dto.OrdersView;
import co.instio.dto.ProductView;
import co.instio.dto.UserView;
import co.instio.entity.Orders;
import co.instio.entity.Products;
import co.instio.entity.Users;
import org.mapstruct.Mapper;

@Mapper
public interface CommonMapper {

    UserView toUserView(Users user);
    ProductView toProductView(Products products);
    OrdersView toOrdersView(Orders orders);

}
