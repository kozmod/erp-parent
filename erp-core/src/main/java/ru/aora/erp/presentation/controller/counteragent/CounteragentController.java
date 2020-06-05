package ru.aora.erp.presentation.controller.counteragent;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.aora.erp.presentation.controller.exception.DtoValidationException;
import ru.aora.erp.presentation.entity.dto.counteragent.CounteragentDto;
import ru.aora.erp.presentation.entity.dto.counteragent.CounteragentDtoMapper;
import ru.aora.erp.presentation.entity.dto.counteragent.CounteragentListDto;
import ru.aora.erp.domain.service.CounteragentService;

import javax.validation.Valid;
import java.util.Map;

import static ru.aora.erp.presentation.entity.dto.counteragent.CounteragentDtoMapper.toCounteragent;

@Controller
@RequestMapping(CounteragentUrl.MAPPING)
public final class CounteragentController {

    private static final String GARANT_MAPPING = "counteragents";
    private static final String CONTRACTOR_DTO_MODEL = "counteragentDto";

    private final CounteragentService counteragentService;

    public CounteragentController(CounteragentService counteragentService) {
        this.counteragentService = counteragentService;
    }

    @GetMapping
    public String counteragentForm(Map<String, Object> model) {
        final CounteragentListDto listDto = CounteragentDtoMapper.toListDto(counteragentService.loadAll());
        model.put(CONTRACTOR_DTO_MODEL, listDto);
        return GARANT_MAPPING;
    }

    @PutMapping
    public @ResponseBody String putCounteragent(@Valid @RequestBody CounteragentDto dto, BindingResult bindingResult) {
        DtoValidationException.throwIfHasErrors(bindingResult);
        return counteragentService.update(toCounteragent(dto)).getMsg();
    }

    @PostMapping
    public @ResponseBody String postCounteragent(@Valid @RequestBody CounteragentDto dto, BindingResult bindingResult) {
        DtoValidationException.throwIfHasErrors(bindingResult);
        counteragentService.create(toCounteragent(dto));
        return "Counteragent was created";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteCounteragent(@PathVariable String id) {
        return counteragentService.delete(id).getMsg();
    }
}
