package com.eascapeco.scinemapr.bo;

import com.eascapeco.scinemapr.api.model.Roles;
import com.eascapeco.scinemapr.api.service.roles.RolesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class BoTest {

    @Autowired
    RolesService rolesService;

    @Test

    void insertRoles() {
        Roles roles = new Roles();
        roles.setRolNm("test_AA");

        Roles savedData = rolesService.createRoles(roles, 0);

        Roles getRoles = rolesService.getOneRoles(savedData.getRolNo());

        assertEquals(savedData.getRolNo(), getRoles.getRolNo());
    }
}
