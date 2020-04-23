package ru.aora.erp.component;

import ru.aora.erp.model.entity.business.IdAuthority;

public enum CoreModuleAuthority implements IdAuthority {

    GET_USERS("Получение пользователей");

    CoreModuleAuthority(String ruleName) {
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
        this.moduleId = moduleId;
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

    private long moduleId;
    private long ruleId;
    private static final String name = "Core";
    private String ruleName;
}
