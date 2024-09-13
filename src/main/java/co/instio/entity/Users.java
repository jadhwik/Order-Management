package co.instio.entity;

import co.instio.dto.validationGroups.CreateGroup;
import co.instio.dto.validationGroups.UpdationGroup;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(groups = CreateGroup.class)
    private String userName;

    @Email(groups = {CreateGroup.class, UpdationGroup.class})
    private String email;

    private String Location;

    private Date joinDate;
}
