package io.web.demo.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class User {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @ManyToOne
    private Role role;
}
