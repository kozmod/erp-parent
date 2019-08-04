package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aora.erp.entity.dto.compose.KsContractCounteragentCollectionDto;

import ru.aora.erp.entity.dto.compose.KsContractCounteragentDto;
import ru.aora.erp.service.ContractService;
import ru.aora.erp.service.CounteragentService;
import ru.aora.erp.service.KsService;

import java.util.List;
import java.util.Map;

import static ru.aora.erp.entity.dto.compose.KsContractCounteragentDtoUtils.sortByGarantDateNaturalOrder;
import static ru.aora.erp.entity.dto.compose.KsContractCounteragentDtoUtils.toKsContractCounteragentDtoList;

@Controller
@RequestMapping("/garantsorted")
public class GarantSortedController {

    private static final String CONTROLLER_MAPPING = "garantsorted";
    private static final String DTO_MODEL = "ksSortDto";

    private KsService ksService;
    private ContractService contractService;
    private CounteragentService counteragentService;

    public GarantSortedController(
            KsService ksService,
            ContractService contractService,
            CounteragentService counteragentService
    ) {
        this.ksService = ksService;
        this.contractService = contractService;
        this.counteragentService = counteragentService;
    }

    @GetMapping
    public String ksSortForm(Map<String, Object> model) {
        final List<KsContractCounteragentDto> ksDtoList = sortByGarantDateNaturalOrder(
                toKsContractCounteragentDtoList(
                        ksService.loadAll(),
                        contractService.loadAll(),
                        counteragentService.loadAll())
        );
        model.put(DTO_MODEL, KsContractCounteragentCollectionDto.of(ksDtoList));
        return CONTROLLER_MAPPING;
    }
}