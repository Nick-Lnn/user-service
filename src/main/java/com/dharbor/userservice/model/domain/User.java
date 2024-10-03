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
    private String accountId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private Boolean isDeleted;

    @Column(nullable = false)
    private Instant createdDate;

    @PrePersist
    protected void onCreate() {
        createdDate = Instant.now();
    }
}
