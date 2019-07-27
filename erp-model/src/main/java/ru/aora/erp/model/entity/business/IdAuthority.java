package ru.aora.erp.model.entity.business;

import org.springframework.security.core.GrantedAuthority;

public interface IdAuthority extends GrantedAuthority {

    long getModuleId();

    void setModuleId(long moduleId);

    long getRuleId();

    void setRuleId(long ruleId);

    String getName();

    String getRuleName();

}
