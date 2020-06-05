package ru.aora.erp.model.entity.db.user;

import org.hibernate.annotations.GenericGenerator;
import ru.aora.erp.model.entity.db.Deactivatable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "j_user_module_role")
public class DbModuleRolePair implements Serializable, Deactivatable {

    @Id
    @GenericGenerator(name = "generator", strategy = "guid")
    @GeneratedValue(generator = "generator")
    @Column(name = "id", columnDefinition = "uniqueidentifier")
    private String id;

    @Column(name = "user_id", columnDefinition = "uniqueidentifier")
    private String userId;

    @Column(name = "module_id", columnDefinition = "uniqueidentifier")
    private String moduleId;

    @Column(name = "role_id", columnDefinition = "uniqueidentifier")
    private String roleId;

    @Column(name = "deactivation_date")
    private LocalDateTime deactivationDate;

    @Column(name = "active_status")
    private Integer activeStatus;

    @PrePersist
    private void prePersist(){
        if(activeStatus == null){
            activeStatus = ACTIVE_ENTITY_FLAG;
        }
    }

//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "module_id", referencedColumnName = "id" )
//    private DbModule module;
//
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "role_id", referencedColumnName = "id")
//    private DbRole role;

    @Transient
    private String moduleName;

    @Transient
    private String roleName;

    public String getId() {
        return id;
    }

    public DbModuleRolePair setId(String id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public DbModuleRolePair setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getModuleId() {
        return moduleId;
    }

    public DbModuleRolePair setModuleId(String moduleId) {
        this.moduleId = moduleId;
        return this;
    }

    public String getRoleId() {
        return roleId;
    }

    public DbModuleRolePair setRoleId(String roleId) {
        this.roleId = roleId;
        return this;
    }

    public LocalDateTime getDeactivationDate() {
        return deactivationDate;
    }

    public DbModuleRolePair setDeactivationDate(LocalDateTime deactivationDate) {
        this.deactivationDate = deactivationDate;
        return this;
    }

    public Integer getActiveStatus() {
        return activeStatus;
    }

    public DbModuleRolePair setActiveStatus(Integer activeStatus) {
        this.activeStatus = activeStatus;
        return this;
    }

    public String getModuleName() {
        return moduleName;
    }

    public DbModuleRolePair setModuleName(String moduleName) {
        this.moduleName = moduleName;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public DbModuleRolePair setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    @Override
    public String toString() {
        return "DbModuleRolePair{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", deactivationDate=" + deactivationDate +
                ", activeStatus=" + activeStatus +
                ", moduleName='" + moduleName + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
