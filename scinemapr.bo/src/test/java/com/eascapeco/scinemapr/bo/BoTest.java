package com.eascapeco.scinemapr.bo;

import com.eascapeco.scinemapr.api.model.Roles;
import com.eascapeco.scinemapr.api.service.roles.RolesService;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("헤이")
    void insertRoles() {
        Roles roles = new Roles();
        roles.setRolNm("test_AA");

        Roles savedData = rolesService.createRoles(roles, 0);

        Roles getRoles = rolesService.getOneRoles(savedData.getRolNo());

        assertEquals(1l, getRoles.getRolNo(),"값틀려!");
        /*
        IllegalStateException thrown = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertEquals("이미 존재하는 회원입니다.", thrown.getMessage());
         */
    }
}
