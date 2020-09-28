package com.eascapeco.scinemapr.api.service.roles;

import com.eascapeco.scinemapr.api.dao.role.RolesMapper;
import com.eascapeco.scinemapr.api.model.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class RolesService {

    private final Logger log = LoggerFactory.getLogger(RolesService.class);

    @Autowired
    private RolesMapper rolesMapper;

    public List<Roles> getRoles() {
        List<Roles> r = this.rolesMapper.selectRoles();

        return r;
    }

    public Roles getOneRoles(Integer id) {
        return this.rolesMapper.selectOneRoles(id);
    }

    /**
     * 
     * @param roles
     * @param admNo
     * @return
     */
    public Roles createRoles(Roles roles, Integer admNo) {
        
        Roles data = new Roles();
        data.setRolNm(roles.getRolNm());
        data.setRegNo(admNo);
        data.setModNo(admNo);
        data.setRegDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        data.setModDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        
        this.rolesMapper.insertRoles(data);
        
        log.info("data -->> {}", data);
        
        return data;
    }
}
