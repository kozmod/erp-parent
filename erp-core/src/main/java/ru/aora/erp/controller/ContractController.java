package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.entity.dto.ContractDto;
import ru.aora.erp.model.entity.business.Contract;
import ru.aora.erp.service.ContractService;

import java.util.Map;

@Controller
@RequestMapping("/contract")
public class ContractController {

    private static final String CONTROLLER_MAPPING = "contracts";
    private static final String DTO_MODEL = "contractDto";
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public String contractForm(
            @RequestParam("id_parent") String id_parent,
            @RequestParam("parent_name") String counteragent_name,
            Map<String, Object> model
    ) {
        final ContractDto contractDto = ContractDto.of(contractService.loadAll());
        model.put(DTO_MODEL, contractDto);
        model.put("id_parent", id_parent);
        model.put("counteragent_name", counteragent_name);
        System.out.println(id_parent);
        return CONTROLLER_MAPPING;
    }

    @PutMapping
    public @ResponseBody
    String putContract(@RequestBody Contract contract) {
        contractService.update(contract);
        return "update";
    }

    @PostMapping
    public @ResponseBody
    String postContract(@RequestBody Contract contract) {
        if (contract != null) {
            contractService.create(contract);
        }
        return "create";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String deleteContract(@PathVariable String id) {
        contractService.delete(id);
        return "delete";
    }
}
