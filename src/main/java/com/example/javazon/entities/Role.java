package com.example.javazon.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ROLES")
public class Role extends SharedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;

    private String roleName;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
