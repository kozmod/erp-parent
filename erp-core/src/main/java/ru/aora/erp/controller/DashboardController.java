package ru.aora.erp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.aora.erp.entity.dto.compose.KsContractCounteragentCollectionDto;
import ru.aora.erp.entity.dto.compose.KsContractCounteragentDto;
import ru.aora.erp.service.ContractService;
import ru.aora.erp.service.CounteragentService;
import ru.aora.erp.service.KsService;
import ru.aora.erp.service.SidebarModulesIdentifiersService;

import java.util.List;
import java.util.Map;

import static ru.aora.erp.component.CoreModuleIdentifier.DASHBOARD_MAPPING;
import static ru.aora.erp.component.CoreModuleIdentifier.ROOT_MAPPING;
import static ru.aora.erp.entity.dto.compose.KsContractCounteragentDtoUtils.sortByGarantDateNaturalOrder;
import static ru.aora.erp.entity.dto.compose.KsContractCounteragentDtoUtils.toKsContractCounteragentDtoList;
import static ru.aora.erp.utils.mvc.MvcUtils.redirectTo;

@Controller
@RequestMapping(DASHBOARD_MAPPING)
public final class DashboardController {

    private static final String DASHBOARD_TEMPLATE = "dashboard";
    private static final String UI_CHANE_NODE_MODEL = "uiChaneNodeModel";
    private static final String DTO_MODEL = "ksSortDto";

    private KsService ksService;
    private ContractService contractService;
    private CounteragentService counteragentService;


    private final SidebarModulesIdentifiersService modulesIdentifiersService;

    @Autowired

    public DashboardController(SidebarModulesIdentifiersService modulesIdentifiersService,
                               KsService ksService,
                               ContractService contractService,
                               CounteragentService counteragentService) {
        this.modulesIdentifiersService = modulesIdentifiersService;
        this.ksService = ksService;
        this.contractService = contractService;
        this.counteragentService = counteragentService;
    }


    @GetMapping
    public String dashboard(Map<String, Object> model) {
        final List<KsContractCounteragentDto> ksDtoList = sortByGarantDateNaturalOrder(
                toKsContractCounteragentDtoList(
                        ksService.loadAll(),
                        contractService.loadAll(),
                        counteragentService.loadAll())
        );
        model.put(DTO_MODEL, KsContractCounteragentCollectionDto.of(ksDtoList));

        model.put(
                UI_CHANE_NODE_MODEL,
                modulesIdentifiersService.loadAll()
        );
        return DASHBOARD_TEMPLATE;
    }

    @RequestMapping(ROOT_MAPPING)
    public String redirectToRoot() {
        return redirectTo(DASHBOARD_TEMPLATE);
    }

}
