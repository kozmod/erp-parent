package ru.aora.erp.controller;

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
import org.springframework.web.servlet.ModelAndView;
import ru.aora.erp.controller.util.ModelAndViewUtils;
import ru.aora.erp.entity.dto.ks.KsDto;
import ru.aora.erp.entity.dto.ks.KsDtoUtils;
import ru.aora.erp.entity.dto.ks.KsListDto;
import ru.aora.erp.service.KsService;

import javax.validation.Valid;
import java.util.Map;

import static ru.aora.erp.entity.dto.ks.KsDtoUtils.toKs;

@Controller
@RequestMapping("/ks")
public final class KsController {

    private static final String CONTROLLER_MAPPING = "kss";
    private static final String DTO_MODEL = "ksDto";

    private static final String ID_PAREN = "id_parent";
    private static final String ID_GRAND_PARENT = "id_grand_parent";
    private static final String PARENT_NAME = "parent_name";
    private static final String GRAND_PARENT_NAME = "grand_parent_name";
    private static final String CONTRACT_NAME = "contract_name";
    private static final String COUNTERAGENT_NAME = "counteragent_name";

    private KsService ksService;

    public KsController(KsService ksService) {
        this.ksService = ksService;
    }

    @SuppressWarnings("Duplicates")
    @GetMapping
    public String ksForm(
            @RequestParam(ID_PAREN) String id_parent,
            @RequestParam(ID_GRAND_PARENT) String id_grand_parent,
            @RequestParam(PARENT_NAME) String contract_name,
            @RequestParam(GRAND_PARENT_NAME) String counteragent_name,
            Map<String, Object> model
    ) {
        final KsListDto ksDto = KsListDto.of(
                KsDtoUtils.toKsDtoList(ksService.loadAll())
        );
        model.put(DTO_MODEL, ksDto);
        model.put(ID_PAREN, id_parent);
        model.put(ID_GRAND_PARENT, id_grand_parent);
        model.put(CONTRACT_NAME, contract_name);
        model.put(COUNTERAGENT_NAME, counteragent_name);
        return CONTROLLER_MAPPING;
    }

    @PutMapping
    public @ResponseBody ModelAndView putKs(@Valid @RequestBody KsDto ks, BindingResult bindingResult) {
        if (ks == null || bindingResult.hasErrors()) {
            return ModelAndViewUtils.error(bindingResult);
        }
        ksService.update(toKs(ks));
        return ModelAndViewUtils.error(bindingResult);
    }

    @PostMapping
    public @ResponseBody ModelAndView postKs(@Valid @RequestBody KsDto ks, BindingResult bindingResult) {
        if (ks == null || bindingResult.hasErrors()) {
            return ModelAndViewUtils.error(bindingResult);
        }
        ksService.create(toKs(ks));
        return ModelAndViewUtils.error(bindingResult);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String deleteKs(@PathVariable String id) {
        ksService.delete(id);
        return "delete";
    }

}
