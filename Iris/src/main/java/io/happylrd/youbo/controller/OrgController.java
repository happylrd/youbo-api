package io.happylrd.youbo.controller;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Org;
import io.happylrd.youbo.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orgs")
public class OrgController {

    @Autowired
    private OrgService orgService;

    @GetMapping
    private ServerResponse<List<Org>> list() {
        return orgService.listOrg();
    }

    @GetMapping("/{id}")
    private ServerResponse<Org> get(@PathVariable("id") Long id) {
        return orgService.getOrg(id);
    }
}
