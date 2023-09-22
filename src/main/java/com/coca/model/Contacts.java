package com.coca.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "CONTACTS")
public class Contacts {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "FULL_NAME")
    private String fullName;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "NOTE")
    private String note;
}
