package com.codingbytime.zexfora.entity.table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Roles")
@Getter
@Setter
public class Role extends BaseObject {

    @Column(name = "NAME")
    private String name;

    @ManyToMany(
        mappedBy = "roles",
        cascade = CascadeType.DETACH
    )
    @JsonBackReference
    private Set<User> users;

    @Override
    public String toString() {
        return "Role{name='" + name + '}';
    }
}

