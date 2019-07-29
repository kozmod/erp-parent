package ru.aora.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.aora.erp.entity.dto.KsDto;
import ru.aora.erp.model.entity.business.Ks;
import ru.aora.erp.service.KsService;

import java.util.Map;

@Controller
@RequestMapping("/Ks")
public class KsController {

    private static final String CONTROLLER_MAPPING = "kss";
    private static final String DTO_MODEL = "ksDto";
    private KsService ksService;

    public KsController(KsService ksService) {
        this.ksService = ksService;
    }

    @GetMapping
    public String ksForm(
            @RequestParam("id_parent") String id_parent,
            @RequestParam("id_grand_parent") String id_grand_parent,
            @RequestParam("parent_name") String contract_name,
            @RequestParam("grand_parent_name") String counteragent_name,
            Map<String, Object> model
    ) {
        final KsDto ksDto = KsDto.of(ksService.loadAll());
        model.put(DTO_MODEL, ksDto);
        model.put("id_parent", id_parent);
        model.put("id_grand_parent", id_grand_parent);
        model.put("contract_name", contract_name);
        model.put("counteragent_name", counteragent_name);
        System.out.println(id_parent);
        return CONTROLLER_MAPPING;
    }

    @PutMapping
    public @ResponseBody
    String putKs(@RequestBody Ks ks) {
        System.out.println(ks);
        ksService.update(ks);
        return "update";
    }

    @PostMapping
    public @ResponseBody
    String postKs(@RequestBody Ks ks) {
        if (ks != null) {
            ksService.create(ks);
            System.out.println("starting " + ks);
        }
        return "create";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    String deleteKs(@PathVariable String id) {
        ksService.delete(id);
        return "delete";
    }

}
