package com.eascapeco.scinemapr.api.service.roles;

import com.eascapeco.scinemapr.api.dao.role.RolesMapper;
import com.eascapeco.scinemapr.api.model.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService {

    @Autowired
    private RolesMapper rolesMapper;

    public List<Roles> getRoles() {
        List<Roles> r = this.rolesMapper.selectRoles();
        for (Roles rs : r) {
            System.out.println(rs.getRolNm());
        }
        return r;
    }
}
