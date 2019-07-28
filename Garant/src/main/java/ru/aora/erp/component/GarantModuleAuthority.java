package ru.aora.erp.component;

import ru.aora.erp.model.entity.IdAuthority;

public enum GarantModuleAuthority implements IdAuthority{

    ADD("Добавить"), DELETE("Удалить");

    GarantModuleAuthority(String ruleName) {
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
        GarantModuleAuthority.moduleId = moduleId;
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
    private static final String name = "Гарант";
    private long ruleId;
    private String ruleName;
}
