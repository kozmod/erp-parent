package ru.aora.erp.model.entity.db;

import java.io.Serializable;
import java.util.StringJoiner;

public class DbModuleRule implements Serializable {

    private long id;
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
    public String toString() {
        return new StringJoiner(", ", DbModuleRule.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
