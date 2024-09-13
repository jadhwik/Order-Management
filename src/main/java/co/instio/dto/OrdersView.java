package co.instio.dto;

import co.instio.enums.EntityStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrdersView {

  private Long orderId;

  private Date orderDate;

  private int quantity;

  private double totalPrice;

  private EntityStatus status;
}
