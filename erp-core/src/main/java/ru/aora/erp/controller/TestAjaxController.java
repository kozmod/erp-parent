package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.entity.UserModuleAuthorityDto;
import ru.aora.erp.model.entity.IdAuthority;
import ru.aora.erp.model.entity.db.DbModule;
import ru.aora.erp.service.AuthorityModulesIdentifiersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.Objects.nonNull;

@Controller
public class TestAjaxController {

    @Autowired
    private AuthorityModulesIdentifiersService authorityModulesIdentifiersService;

    @RequestMapping(value = "/updateUserRoles",method = RequestMethod.POST, consumes="application/json")
    public @ResponseBody
    String putUser(@RequestBody UserModuleAuthorityDto moduleAuthorityDto) {
        System.out.println(moduleAuthorityDto);
        return "update";
    }

//    private List<IdAuthority> conwertModules(UserModuleAuthorityDto dto){
//        authorityModulesIdentifiersService.loadAll();
//    }
//
//
//    private Optional<DbModule> tryFindModule(Map<String, Map<String,Boolean>> modules, List<IdAuthority> allAuthorities) {
//        final List<IdAuthority> toDelete = new ArrayList<>();
//        final List<IdAuthority> toAdd= new ArrayList<>();
//        return Optional.empty();
//    }

//    private Pair<List<IdAuthority> ,List<IdAuthority> > tryFindModuleRole(String moduleName,Map<String,Boolean> ropes){
//
//    }
}
