package com.eascapeco.scinemapr.bo.controller.roles;

import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.Menu;
import com.eascapeco.scinemapr.api.model.Roles;
import com.eascapeco.scinemapr.api.service.roles.RolesService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import javax.tools.DocumentationTool.Location;

@RestController
@RequestMapping("/api")
public class BoRolesController {

    private final Logger log = LoggerFactory.getLogger(BoRolesController.class);

    @Autowired
    private RolesService rolesService;

    @GetMapping("/roles")
    public List<Roles> getRoles() {
        return this.rolesService.getRoles();
    }

    @GetMapping("/roles/{id]")
    public Roles getOneRoles(@PathVariable Integer id) {
        return this.rolesService.getOneRoles(id);
    }

    @PostMapping("/roles")
    public ResponseEntity<Roles> saveRoles(@AuthenticationPrincipal Admin loginUser, @RequestBody Roles roles) {
        log.info("{}", loginUser);
        
        Roles savedRoles = this.rolesService.createRoles(roles, loginUser.getAdmNo());
        
        URI localtion = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{rolNo}")
                .buildAndExpand(savedRoles.getRolNo())
                .toUri();
        
        return ResponseEntity.created(localtion).build();
    }
}
