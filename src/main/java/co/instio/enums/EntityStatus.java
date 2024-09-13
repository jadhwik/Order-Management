package co.instio.enums;

import lombok.Getter;

public enum EntityStatus {
    ORDRERED(10),
    SHIPPED(20),
    PENDING(30),
    OUT_FOR_DELIVERY(40),
    COMPLETED(50);

    @Getter
    private final int val;

    EntityStatus(int val) {
        this.val = val;
    }
}
