package co.instio.entity;

import co.instio.enums.EntityStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import  java.util.*;

@Entity
@Getter
@Setter
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    @OneToMany
    @JoinColumn(name="orderId")
    private List<Products> products;

    private Date orderDate;

    private int quantity;

    private double totalPrice;

    @Enumerated(EnumType.ORDINAL)
    private EntityStatus status;
}
