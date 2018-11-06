package ru.aora.erp.model.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Collection;

@Entity
@Table(name = "Users")
public class DbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @OneToMany
    @JoinTable(
            name = "j_Users_Modules_Rule",
            joinColumns = {@JoinColumn(name="id_User",referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name="id_User", referencedColumnName="id_User")}
    )
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Transient
    private Collection<DbModule> authorities;

    @Column(name = "account_non_expired", columnDefinition = "BOOLEAN")
    private boolean accountNonExpired;
    @Column(name = "account_non_locked", columnDefinition = "BOOLEAN")
    private boolean accountNonLocked;
    @Column(name = "credentials_non_expired", columnDefinition = "BOOLEAN")
    private boolean credentialsNonExpired;
    @Column(name = "enabled", columnDefinition = "BOOLEAN")
    private boolean enabled;

    @Column(name = "user_name", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Transient
    private boolean del;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Collection<DbModule> getAuthorities() {
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

    public String getMail() {
        return mail;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAuthorities(Collection<DbModule> authorities) {
        this.authorities = authorities;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private int id;
        private Collection<DbModule> authorities;
        private boolean accountNonExpired;
        private boolean accountNonLocked;
        private boolean credentialsNonExpired;
        private boolean enabled;
        private String username;
        private String password;
        private String mail;
        private boolean del;

        private UserBuilder() {
        }

        public UserBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder withAuthorities(Collection<DbModule> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserBuilder withAccountNonExpired(boolean accountNonExpired) {
            this.accountNonExpired = accountNonExpired;
            return this;
        }

        public UserBuilder withAccountNonLocked(boolean accountNonLocked) {
            this.accountNonLocked = accountNonLocked;
            return this;
        }

        public UserBuilder withCredentialsNonExpired(boolean credentialsNonExpired) {
            this.credentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public UserBuilder withEnabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public UserBuilder withUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withMail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserBuilder withDel(boolean del) {
            this.del = del;
            return this;
        }

        public DbUser build() {
            DbUser user = new DbUser();
            user.setId(id);
            user.setAuthorities(authorities);
            user.setAccountNonExpired(accountNonExpired);
            user.setAccountNonLocked(accountNonLocked);
            user.setCredentialsNonExpired(credentialsNonExpired);
            user.setEnabled(enabled);
            user.setUsername(username);
            user.setPassword(password);
            user.setMail(mail);
            user.setDel(del);
            return user;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", authorities=" + authorities +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", del=" + del +
                '}';
    }
}
