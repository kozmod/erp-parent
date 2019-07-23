package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aora.erp.entity.CounteragentDto;
import ru.aora.erp.model.entity.counteragent.Counteragent;
import ru.aora.erp.service.AuthorityModulesIdentifiersService;
import ru.aora.erp.service.impl.CounteragentServiceImpl;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/counteragent")
public class CounteragentController {

    private static final String GARANT_MAPPING = "counteragents";
    private static final String CONTRACTOR_DTO_MODEL = "counteragentDto";
    private static final String MODULE_AUTHORITY_DTO_LIST_MODEL = "moduleAuthorityDtoList";

    private CounteragentServiceImpl counteragentService;
    private AuthorityModulesIdentifiersService authorityModulesIdentifiersService;

    public CounteragentController(CounteragentServiceImpl counteragentService) {
        this.counteragentService = counteragentService;

    }



        @GetMapping
        public String userForm(Map<String, Object> model, Principal principal) {
        final CounteragentDto counteragentDto =  CounteragentDto.of(counteragentService.loadAll());
        model.put(CONTRACTOR_DTO_MODEL, counteragentDto);
        return GARANT_MAPPING;
        }

        @PutMapping
        public @ResponseBody String putCounteragent(@RequestBody Counteragent counteragent) {
        counteragentService.update(counteragent);
        return "update";
        }

        @PostMapping
        public @ResponseBody String postCounteragent(@RequestBody Counteragent counteragent) {
        if(counteragent!=null) { counteragentService.create(counteragent); }
        return "create";
        }

        @DeleteMapping("/{id}")
        public @ResponseBody String deleteCounteragent(@PathVariable String id) {
            counteragentService.delete(id);
            return "delete";
        }



}
