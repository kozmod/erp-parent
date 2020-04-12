package ru.aora.erp.model.entity.db;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name = "User_Authority")
public class DbUserAuthority implements Serializable {

    private static final long serialVersionUID = 5030424785140625910L;

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;
    @Column(name = "authority")
    private String authority;
    @Column(name = "sub_authority")
    private String subAuthority;

    @Column(name = "version_timestamp",columnDefinition = "TIMESTAMP")
    private String  versionTimestamp;

    @Column(name = "creation_date")
    private String creationDate;

    public String getId() {
        return id;
    }

    public DbUserAuthority setId(String id) {
        this.id = id;
        return this;
    }

    public String getAuthority() {
        return authority;
    }

    public DbUserAuthority setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public String getSubAuthority() {
        return subAuthority;
    }

    public DbUserAuthority setSubAuthority(String subAuthority) {
        this.subAuthority = subAuthority;
        return this;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public DbUserAuthority setCreationDate(String creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getVersionTimestamp() {
        return versionTimestamp;
    }

    public DbUserAuthority setVersionTimestamp(String versionTimestamp) {
        this.versionTimestamp = versionTimestamp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DbUserAuthority)) return false;

        DbUserAuthority that = (DbUserAuthority) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(authority, that.authority)) return false;
        if (!Objects.equals(creationDate, that.creationDate)) return false;
        if (!Objects.equals(versionTimestamp, that.versionTimestamp)) return false;
        return Objects.equals(subAuthority, that.subAuthority);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (authority != null ? authority.hashCode() : 0);
        result = 31 * result + (subAuthority != null ? subAuthority.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (versionTimestamp != null ? versionTimestamp.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbUserAuthority.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("authority='" + authority + "'")
                .add("subAuthority='" + subAuthority + "'")
                .add("versionTimestamp='" + versionTimestamp + "'")
                .add("creationDate='" + creationDate + "'")
                .toString();
    }
}
