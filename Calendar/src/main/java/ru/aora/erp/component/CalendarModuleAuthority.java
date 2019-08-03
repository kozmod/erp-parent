package ru.aora.erp.component;

import ru.aora.erp.model.entity.business.IdAuthority;

public enum CalendarModuleAuthority implements IdAuthority{

    ADD("Добавить"), DELETE("Удалить");

    CalendarModuleAuthority(String ruleName) {
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
        CalendarModuleAuthority.moduleId = moduleId;
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
    private static final String name = "Календарь";
    private long ruleId;
    private String ruleName;
}
