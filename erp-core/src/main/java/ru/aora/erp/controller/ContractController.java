package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aora.erp.entity.ContractDto;
import ru.aora.erp.model.entity.contract.Contract;
import ru.aora.erp.service.impl.ContractServiceImpl;
import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("/contract")
public class ContractController {

    private static final String CONTROLLER_MAPPING = "contracts";
    private static final String DTO_MODEL = "contractDto";
    private ContractServiceImpl contractService;

    public ContractController(ContractServiceImpl contractService) {this.contractService = contractService; }

        @GetMapping
        public String userForm(@RequestParam("id_parent") String id_parent, @RequestParam("parent_name") String counteragent_name, Map<String, Object> model, Principal principal) {
        final ContractDto contractDto =  ContractDto.of(contractService.loadAll());
        model.put(DTO_MODEL, contractDto);
        model.put("id_parent",id_parent);
        model.put("counteragent_name",counteragent_name);
        System.out.println(id_parent);
        return CONTROLLER_MAPPING;
        }

        @PutMapping
        public @ResponseBody String putContract(@RequestBody Contract contract) {
        System.out.println(contract);
        contractService.update(contract);

        return "update";
        }

        @PostMapping
        public @ResponseBody String postContract(@RequestBody Contract contract) {
        if(contract!=null) { contractService.create(contract); }
            System.out.println("starting"+contract);
        return "create";
        }

        @DeleteMapping("/{id}")
        public @ResponseBody String deleteContract(@PathVariable String id) {

        contractService.delete(id);
            return "delete";
        }



}
