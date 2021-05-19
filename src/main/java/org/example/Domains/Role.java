package org.example.Domains;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "role")
    private String name;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

    public Role() {}

    public Role(String role) {
        this.name = role;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return name;
    }
    public void setRole(String role) {
        this.name = role;
    }

    public Set<User> getUsers() {
        return users;
    }
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + name + '\'' +
                '}';
    }
}
