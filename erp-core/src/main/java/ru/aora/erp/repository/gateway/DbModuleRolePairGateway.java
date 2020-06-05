package ru.aora.erp.repository.gateway;


import ru.aora.erp.model.entity.db.Deactivatable;
import ru.aora.erp.model.entity.db.user.DbModule;
import ru.aora.erp.model.entity.db.user.DbModuleRolePair;
import ru.aora.erp.model.entity.db.user.DbRole;
import ru.aora.erp.repository.jpa.JpaModuleRepository;
import ru.aora.erp.repository.jpa.JpaModuleRolePairRepository;
import ru.aora.erp.repository.jpa.JpaRoleRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static ru.aora.erp.model.entity.db.Deactivatable.INACTIVE_ENTITY_FLAG;
import static ru.aora.erp.repository.gateway.GatewayUtils.groupFunctions;

@Transactional
public class DbModuleRolePairGateway {

    private final JpaModuleRepository moduleRepository;
    private final JpaRoleRepository roleRepository;
    private final JpaModuleRolePairRepository moduleRolePairRepository;

    private final Function<Iterable<DbModule>, Map<String, DbModule>> gropeModuleByName = groupFunctions(DbModule::getName);
    private final Function<Iterable<DbRole>, Map<String, DbRole>> gropeRoleByName = groupFunctions(DbRole::getName);

    private final Function<Iterable<DbModule>, Map<String, DbModule>> gropeModuleById = groupFunctions(DbModule::getId);
    private final Function<Iterable<DbRole>, Map<String, DbRole>> gropeRoleById = groupFunctions(DbRole::getId);

    public DbModuleRolePairGateway(JpaModuleRepository moduleRepository,
                                   JpaRoleRepository roleRepository,
                                   JpaModuleRolePairRepository moduleRolePairRepository) {
        this.moduleRepository = moduleRepository;
        this.roleRepository = roleRepository;
        this.moduleRolePairRepository = moduleRolePairRepository;
    }

    UnaryOperator<Collection<DbModuleRolePair>> addModuleRoleNameByIdFunction() {
        Map<String, DbModule> existsModules = gropeModuleById.apply(moduleRepository.findAll());
        Map<String, DbRole> existsRoles = gropeRoleById.apply(roleRepository.findAll());
        return pairs -> {
            if (pairs != null && !pairs.isEmpty()) {
                for (var pair : pairs) {
                    var module = existsModules.get(pair.getModuleId());
                    var role = existsRoles.get(pair.getRoleId());
                    if (module != null && role != null) {
                        pair.setModuleName(module.getName());
                        pair.setRoleName(role.getName());
                    }
                }
            }
            return pairs;
        };
    }

    Collection<DbModuleRolePair> prepareRoleToUpdate(Collection<DbModuleRolePair> source, String UserId) {
        return prepareToUpdate(
                tryCreateAuthoritiesAndIds(source),
                removeAllDeactivated(
                        moduleRolePairRepository.findAllActiveByUserId(UserId)
                )
        );
    }

    /**
     * Set User id to DbModuleRolePair not necessary
     */
    Collection<DbModuleRolePair> tryCreateAuthoritiesAndIds(Collection<DbModuleRolePair> source) {
        if (source == null) {
            return Collections.emptyList();
        }
        Map<String, DbRole> existsRoles = gropeRoleByName.apply(roleRepository.findAll());
        Map<String, DbModule> existsModules = gropeModuleByName.apply(moduleRepository.findAll());

        for (var pair : source) {
            var existsRole = existsRoles.computeIfAbsent(
                    pair.getRoleName(),
                    name -> roleRepository.save(
                            new DbRole().setName(pair.getRoleName())
                    )
            );
            pair.setRoleId(existsRole.getId());
            var existsModule = existsModules.computeIfAbsent(
                    pair.getModuleName(),
                    name -> moduleRepository.save(
                            new DbModule().setName(pair.getModuleName())
                    )
            );
            pair.setModuleId(existsModule.getId());
        }
        return source;
    }

    Collection<DbModuleRolePair> prepareToUpdate(Collection<DbModuleRolePair> sources, Collection<DbModuleRolePair> targets) {
        if (sources == null || sources.isEmpty()) {
            return deactivateAll(targets);
        }
        if (targets == null || targets.isEmpty()) {
            return sources;
        }
        Collection<DbModuleRolePair> result = new ArrayList<>(targets.size() + sources.size());
        Map<String, DbModuleRolePair> activeTargetsByBusinessKey = new HashMap<>(targets.size(), 1.1F);
        for (var target : targets) {
            if (target.isActive()) {
                activeTargetsByBusinessKey.put(moduleRoleIdBusinessKey(target), target);
            }
        }

        for (var source : sources) {
            var businessKey = moduleRoleIdBusinessKey(source);
            var target = activeTargetsByBusinessKey.remove(businessKey);
            if (target == null) {
                result.add(source);
            }
        }

        result.addAll(
                deactivateAll(
                        activeTargetsByBusinessKey.values()
                )
        );
        return result;
    }

    Collection<DbModuleRolePair> deactivateAll(Collection<DbModuleRolePair> moduleRolePairs) {
        if (moduleRolePairs != null) {
            for (var moduleRolePair : moduleRolePairs) {
                setDeactivated(moduleRolePair);
            }
        }
        return moduleRolePairs;
    }

    <T extends Deactivatable> Collection<T> removeAllDeactivated(Collection<T> deactivatables) {
        if (deactivatables == null) {
            return Collections.emptyList();
        }
        Collection<T> res = new ArrayList<>(deactivatables.size());
        for (T val : deactivatables) {
            if (val != null && val.isActive()) {
                res.add(val);
            }

        }
        return res;
    }


    private DbModuleRolePair setDeactivated(DbModuleRolePair pair) {
        return Objects.requireNonNull(pair)
                .setActiveStatus(INACTIVE_ENTITY_FLAG)
                .setDeactivationDate(LocalDateTime.now());
    }


    private static String moduleRoleIdBusinessKey(DbModuleRolePair pair) {
        if (pair.getModuleName() == null || pair.getRoleName() == null) {
            throw new IllegalArgumentException(
                    String.format("Entity has to contain module ID and role ID: %s", pair)
            );
        }
        return pair.getModuleName() + pair.getRoleName();
    }

}
