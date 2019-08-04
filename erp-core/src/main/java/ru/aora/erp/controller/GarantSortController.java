package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aora.erp.entity.dto.combine.KsContractCounteragentCollectionDto;
import ru.aora.erp.entity.dto.combine.KsContractCounteragentDtoUtils;
import ru.aora.erp.entity.dto.ks.KsDtoUtils;
import ru.aora.erp.entity.dto.ks.KsListDto;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.service.ContractService;
import ru.aora.erp.service.CounteragentService;
import ru.aora.erp.service.KsService;

import java.util.Map;

@Controller
@RequestMapping("/Garantsort")
public class GarantSortController {

    private static final String CONTROLLER_MAPPING = "garantsort";
    private static final String DTO_MODEL = "ksSortDto";
    public KsContractCounteragentDtoUtils ksContractCounteragentDtoUtils;
    private KsService ksService;
    private ContractService contractService;
    private CounteragentService counteragentService;
    public GarantSortController(KsService ksService, ContractService contractService,CounteragentService counteragentService) {
        this.ksService = ksService;
        this.contractService = contractService;
        this.counteragentService = counteragentService;
    }

    @GetMapping
    public String ksSortForm(Map<String, Object> model) {
        final KsContractCounteragentCollectionDto ksDto =  KsContractCounteragentCollectionDto.of(ksContractCounteragentDtoUtils.sortByGarantDateNaturalOrder(ksContractCounteragentDtoUtils.toKsContractCounteragentDtoList(ksService.loadAll(),contractService.loadAll(),counteragentService.loadAll())));
        model.put(DTO_MODEL, ksDto);
        return CONTROLLER_MAPPING;
    }

}
