package co.instio.repo;

import co.instio.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepo  extends JpaRepository<Orders ,Long> {
}
