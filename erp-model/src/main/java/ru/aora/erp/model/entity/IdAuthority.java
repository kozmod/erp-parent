package ru.aora.erp.model.entity;

import org.springframework.security.core.GrantedAuthority;

import static org.apache.logging.log4j.util.Strings.isBlank;

public interface IdAuthority extends GrantedAuthority {

    long getModuleId();

    void setModuleId(long moduleId);

    long getRuleId();

    void setRuleId(long ruleId);

    String getName();

    String getRuleName();

}
