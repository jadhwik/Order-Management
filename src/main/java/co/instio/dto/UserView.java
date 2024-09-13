package co.instio.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserView {

    private String userName;

    private String email;

    private String location;

    private Date joinDate;
}
