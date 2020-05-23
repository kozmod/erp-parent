package ru.aora.erp.model.entity.db.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_module")
public class DbModule {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier", unique = true)
    private String id;

    @Column(name = "name", unique = true)
    private String name;

//    @ManyToMany(mappedBy = "authorities", fetch=FetchType.LAZY)
//    private Collection<DbUser> users;

//    @ManyToMany(fetch=FetchType.EAGER)
//    @JoinTable(
//            name = "j_user_module_role",
//            joinColumns = @JoinColumn(
//                    name = "module_id", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(
//                    name = "role_id", referencedColumnName = "id"))
//    private Collection<DbRole> roles;

    public String getId() {
        return id;
    }

    public DbModule setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DbModule setName(String name) {
        this.name = name;
        return this;
    }

//    public Collection<DbRole> getRoles() {
//        return roles;
//    }
//
//    public DbModule setRoles(Collection<DbRole> subAuthorities) {
//        this.roles = subAuthorities;
//        return this;
//    }

    //    public Collection<DbUser> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Collection<DbUser> users) {
//        this.users = users;
//    }

    @Override
    public String toString() {
        return "DbAuthority{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
//                ", users=" + users +
//                ", subAuthorities=" + roles +
                '}';
    }
}
