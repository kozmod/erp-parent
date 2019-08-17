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
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.entity.dto.CounteragentDto;
import ru.aora.erp.model.entity.business.Counteragent;
import ru.aora.erp.service.CounteragentService;

import java.util.Map;

@Controller
@RequestMapping("/counteragent")
public final class CounteragentController {

    private static final String GARANT_MAPPING = "counteragents";
    private static final String CONTRACTOR_DTO_MODEL = "counteragentDto";
    private static final String MODULE_AUTHORITY_DTO_LIST_MODEL = "moduleAuthorityDtoList";

    private CounteragentService counteragentService;

    @Autowired
    public CounteragentController(CounteragentService counteragentService) {
        this.counteragentService = counteragentService;

    }


    @GetMapping
    public String counteragentForm(Map<String, Object> model) {
        final CounteragentDto counteragentDto = CounteragentDto.of(counteragentService.loadAll());
        model.put(CONTRACTOR_DTO_MODEL, counteragentDto);
        return GARANT_MAPPING;
    }

    @PutMapping
    public @ResponseBody
    String putCounteragent(@RequestBody Counteragent counteragent) {
        counteragentService.update(counteragent);
        return "update";
    }

    @PostMapping
    public @ResponseBody
    String postCounteragent(@RequestBody Counteragent counteragent) {
        if (counteragent != null) {
            counteragentService.create(counteragent);
        }
        return "create";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String deleteCounteragent(@PathVariable String id) {
        counteragentService.delete(id);
        return "delete";
    }
}
