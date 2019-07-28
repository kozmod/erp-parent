package ru.aora.erp.component;

import ru.aora.erp.model.entity.IdAuthority;

public enum TestModuleAuthority implements IdAuthority{

    ADD("Добавить"), DELETE("Удалить");

    TestModuleAuthority(String ruleName) {
        this.ruleName = ruleName;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    @Override
    public long getModuleId() {
        return moduleId;
    }

    @Override
    public void setModuleId(long moduleId) {
        TestModuleAuthority.moduleId = moduleId;
    }

    @Override
    public long getRuleId() {
        return ruleId;
    }

    @Override
    public void setRuleId(long ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRuleName() {
        return ruleName;
    }

    private static long moduleId;
    private static final String name = "TEST";
    private long ruleId;
    private String ruleName;
}
