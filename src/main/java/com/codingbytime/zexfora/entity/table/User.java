package com.codingbytime.zexfora.entity.table;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER")
public class User extends BaseObject {


    @Column(name = "MAIL", unique = true, nullable = false)
    private String mail;


    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<Website> userWebsites = new HashSet<>();

    @ManyToMany(
        cascade = CascadeType.DETACH,
        fetch = FetchType.EAGER
    )
    @JoinTable(
        name = "USER_ROLES",
        joinColumns = @JoinColumn(name = "USER_ID"),
        inverseJoinColumns = @JoinColumn(name = "ROLE_ID")
    )
    private Set<Role> roles;
}
