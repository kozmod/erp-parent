package ru.aora.erp.model.entity.db.user;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import ru.aora.erp.model.entity.db.Deactivatable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.StringJoiner;

@Entity
@Table(name = "[user]")
public class DbUser implements Serializable, Deactivatable {

    private static final long serialVersionUID = -8446608340994054062L;

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "account_non_expired")
    private boolean accountNonExpired;

    @Column(name = "account_non_locked")
    private boolean accountNonLocked;

    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "mail")
    private String mail;

    @Column(name = "employee_position")
    private String employeePosition;

    @Column(name = "deactivation_date")
    private LocalDateTime deactivationDate;

    @Column(name = "active_status")
    private Integer activeStatus;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @Where(clause = "deactivation_date is NULL" )
    private Collection<DbModuleRolePair> authorities;

    @PrePersist
    private void prePersist(){
        if(activeStatus == null){
            activeStatus = ACTIVE_ENTITY_FLAG;
        }
    }

    public String getId() {
        return id;
    }

    public DbUser setId(String id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public DbUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DbUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public DbUser setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public DbUser setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public DbUser setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public DbUser setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
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

//    public LocalDateTime getCreationDate() {
//        return creationDate;
//    }
//
//    public DbUser setCreationDate(LocalDateTime creationDate) {
//        this.creationDate = creationDate;
//        return this;
//    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public DbUser setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public Collection<DbModuleRolePair> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<DbModuleRolePair> dbAuthorities) {
        this.authorities = dbAuthorities;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public DbUser setActiveStatus(Integer deactivated) {
        this.activeStatus = deactivated;
        return this;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbUser.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("accountNonExpired=" + accountNonExpired)
                .add("accountNonLocked=" + accountNonLocked)
                .add("credentialsNonExpired=" + credentialsNonExpired)
                .add("enabled=" + enabled)
                .add("firstName='" + firstName + "'")
                .add("surname='" + surname + "'")
                .add("patronymic='" + patronymic + "'")
                .add("phoneNumber='" + phoneNumber + "'")
                .add("mail='" + mail + "'")
                .add("employeePosition='" + employeePosition + "'")
                .add("authorities=" + authorities)
                .add("deactivationDate=" + deactivationDate)
                .add("deactivated=" + activeStatus)
                .toString();
    }
}
