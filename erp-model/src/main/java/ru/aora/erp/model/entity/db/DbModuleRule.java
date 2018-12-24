package ru.aora.erp.model.entity.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.StringJoiner;

@Entity
@Table(name = "Modules_Access_Rules")
public class DbModuleRule implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;

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

    public static DbModuleRuleBuilder builder(){
        return new DbModuleRuleBuilder();
    }

    public static  final class DbModuleRuleBuilder {
        private long id;
        private String name;

        private DbModuleRuleBuilder() {
        }

        public static DbModuleRuleBuilder aDbModuleRule() {
            return new DbModuleRuleBuilder();
        }

        public DbModuleRuleBuilder withId(long id) {
            this.id = id;
            return this;
        }

        public DbModuleRuleBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DbModuleRule build() {
            DbModuleRule dbModuleRule = new DbModuleRule();
            dbModuleRule.setId(id);
            dbModuleRule.setName(name);
            return dbModuleRule;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DbModuleRule)) return false;

        DbModuleRule that = (DbModuleRule) o;

        if (id != that.id) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DbModuleRule.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
