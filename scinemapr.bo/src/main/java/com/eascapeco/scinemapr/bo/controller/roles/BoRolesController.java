package com.eascapeco.scinemapr.bo.controller.roles;

import com.eascapeco.scinemapr.api.model.Roles;
import com.eascapeco.scinemapr.api.service.roles.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoRolesController {

    @Autowired
    private RolesService rolesService;

    @GetMapping("/roles")
    public List<Roles> getRoles() {
        return this.rolesService.getRoles();
    }
}
