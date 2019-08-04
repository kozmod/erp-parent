package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.entity.dto.UserIdModuleAuthorityDto;
import ru.aora.erp.service.AuthorityModulesIdentifiersService;

@Controller
public final class TestAjaxController {

    @Autowired
    private AuthorityModulesIdentifiersService authorityModulesIdentifiersService;

    @RequestMapping(value = "/updateUserRoles", method = RequestMethod.POST, consumes = "application/json")
    public @ResponseBody
    String putUser(@RequestBody UserIdModuleAuthorityDto moduleAuthorityDto) {
        System.out.println(moduleAuthorityDto);
        return "update";
    }

//    private List<IdAuthority> conwertModules(UserIdModuleAuthorityDto dto){
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
