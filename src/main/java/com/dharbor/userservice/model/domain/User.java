package com.dharbor.userservice.model.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;


/**
 * @author Nicolas
 */
@Data
@Entity
@Table(name = Constants.UserTable.NAME)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column()
    private Boolean isDeleted;

    @Column(nullable = false)
    private Instant createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = Instant.now();
    }
}
