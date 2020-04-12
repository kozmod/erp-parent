package ru.aora.erp.model.entity.db.user;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_authority")
public class DbAuthority {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "name", unique = true)
    private String name;

//    @ManyToMany(mappedBy = "authorities", fetch=FetchType.LAZY)
//    private Collection<DbUser> users;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name = "user_authority_user_sub_authority",
            joinColumns = @JoinColumn(
                    name = "authority_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "sub_authority_id", referencedColumnName = "id"))
    private Collection<DbSubAuthority> subAuthorities;

    public String getId() {
        return id;
    }

    public DbAuthority setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public DbAuthority setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<DbSubAuthority> getSubAuthorities() {
        return subAuthorities;
    }

    public DbAuthority setSubAuthorities(Collection<DbSubAuthority> subAuthorities) {
        this.subAuthorities = subAuthorities;
        return this;
    }

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
                ", subAuthorities=" + subAuthorities +
                '}';
    }
}
