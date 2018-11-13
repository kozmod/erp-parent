package ru.aora.erp.component;

import ru.aora.erp.model.entity.IdAuthority;

public enum TestModuleAuthority implements IdAuthority{

    ADD, DELETE;

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

    private static long moduleId;
    private long ruleId;
}
