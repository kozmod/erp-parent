package ru.aora.erp.model.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@Table(name = "Modules")
public class DbModule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;

    @Transient
    private Set<DbModuleRule> moduleRoles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DbModuleRule> getModuleRoles() {
        return moduleRoles;
    }

    public void setModuleRoles(Set<DbModuleRule> moduleRoles) {
        this.moduleRoles = moduleRoles;
    }

    public static DbModuleBuilder builder() {
        return new DbModuleBuilder();
    }

    public final static class DbModuleBuilder {
        private long id;
        private String name;
        private Set<DbModuleRule> moduleRoles;

        private DbModuleBuilder() {
        }


        public DbModuleBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public DbModuleBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DbModuleBuilder withModuleRoles(Set<DbModuleRule> moduleRoles) {
            this.moduleRoles = moduleRoles;
            return this;
        }

        public DbModule build() {
            DbModule dbModule = new DbModule();
            dbModule.setId(id);
            dbModule.setName(name);
            dbModule.setModuleRoles(moduleRoles);
            return dbModule;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DbModule)) return false;

        DbModule module = (DbModule) o;

        if (id != module.id) {
            return false;
        }
        return name.equals(module.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbModule.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("moduleRoles=" + moduleRoles)
                .toString();
    }
}
