package ru.aora.erp.presentation.controller.contract;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.presentation.controller.exception.DtoValidationException;
import ru.aora.erp.presentation.entity.dto.contract.ContractDto;
import ru.aora.erp.presentation.entity.dto.contract.ContractListDto;
import ru.aora.erp.domain.service.ContractService;

import javax.validation.Valid;
import java.util.Map;

import static ru.aora.erp.presentation.entity.dto.contract.ContractDtoMapper.toContract;
import static ru.aora.erp.presentation.entity.dto.contract.ContractDtoMapper.toListDto;

@Controller
@RequestMapping(ContractUrl.MAPPING)
public final class ContractController {

    private static final String CONTROLLER_MAPPING = "contracts";
    private static final String DTO_MODEL = "contractDto";

    private static final String ID_PARENT = "id_parent";
    private static final String PARENT_NAME = "parent_name";
    private static final String COUNTERAGENT_NAME = "counteragent_name";

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping
    public String contractForm(
            @RequestParam(ID_PARENT) String id_parent,
            @RequestParam(PARENT_NAME) String counteragent_name,
            Map<String, Object> model
    ) {
        final ContractListDto contractDto = toListDto(contractService.loadAll());
        model.put(DTO_MODEL, contractDto);
        model.put(ID_PARENT, id_parent);
        model.put(COUNTERAGENT_NAME, counteragent_name);
        return CONTROLLER_MAPPING;
    }

    @PutMapping
    public @ResponseBody String putContract(@Valid @RequestBody ContractDto dto, BindingResult bindingResult) {
        DtoValidationException.throwIfHasErrors(bindingResult);
        return  contractService.update(toContract(dto)).getMsg();
    }

    @PostMapping
    public @ResponseBody String postContract(@Valid @RequestBody ContractDto dto, BindingResult bindingResult) {
        DtoValidationException.throwIfHasErrors(bindingResult);
        contractService.create(toContract(dto));
        return "Contract was created";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteContract(@PathVariable String id) {
        return contractService.delete(id).getMsg();
    }
}
