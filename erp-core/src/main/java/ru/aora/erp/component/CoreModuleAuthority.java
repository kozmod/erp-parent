package ru.aora.erp.component;

import ru.aora.erp.model.entity.IdAuthority;

public enum CoreModuleAuthority implements IdAuthority {

    GET_USERS;

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

    private long moduleId;
    private long ruleId;
}
