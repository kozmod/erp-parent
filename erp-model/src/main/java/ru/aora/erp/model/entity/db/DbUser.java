package ru.aora.erp.model.entity.db;

import java.util.Set;
import java.util.StringJoiner;

public class DbUser {

    private long id;
    private Set<DbModule> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String patronymic;
    private String phoneNumber;
    private String mail;
    private String employeePosition;
    private boolean del;

    public long getId() {
        return id;
    }

    public DbUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public DbUser setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public DbUser setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public DbUser setPatronymic(String patronymic) {
        this.patronymic = patronymic;
        return this;
    }

    public Set<DbModule> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DbUser setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public DbUser setAuthorities(Set<DbModule> authorities) {
        this.authorities = authorities;
        return this;
    }

    public DbUser setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public DbUser setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public DbUser setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public DbUser setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public DbUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public DbUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public DbUser setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }

    public DbUser setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
        return this;
    }

    public boolean isDel() {
        return del;
    }

    public DbUser setDel(boolean del) {
        this.del = del;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbUser.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("authorities=" + authorities)
                .add("accountNonExpired=" + accountNonExpired)
                .add("accountNonLocked=" + accountNonLocked)
                .add("credentialsNonExpired=" + credentialsNonExpired)
                .add("enabled=" + enabled)
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("firstName='" + firstName + "'")
                .add("surname='" + surname + "'")
                .add("patronymic='" + patronymic + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("mail='" + mail + "'")
                .add("employeePosition='" + employeePosition + "'")
                .add("del=" + del)
                .toString();
    }
}
