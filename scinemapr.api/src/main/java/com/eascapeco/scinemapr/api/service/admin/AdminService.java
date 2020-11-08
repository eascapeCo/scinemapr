package com.eascapeco.scinemapr.api.service.admin;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.eascapeco.scinemapr.api.dao.admin.AdminMapper;
import com.eascapeco.scinemapr.api.model.Admin;
import com.eascapeco.scinemapr.api.model.RefreshToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 관리자 service
 *
 * @author jaehankim
 * @Date 2019. 10. 10
 */
@Service
public class AdminService {
    private final Logger log = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    public AdminMapper AdminMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Admin getAdmin(Admin admin) {

        Admin adminInfo = AdminMapper.selectAdmin(admin.getId());

        if (adminInfo == null) {
            throw new UsernameNotFoundException("User not found with id: " + admin.getId());
        } else {
            // 비밀번호 일치 검사
            if (!passwordEncoder.matches(admin.getPwd(), adminInfo.getPwd())) {
                throw new BadCredentialsException("Passwords do not match.");
            }
        }
        return adminInfo;
    }

    public void insertRefreshToken(RefreshToken tkn) {
        AdminMapper.insertRefreshToken(tkn);

    }

    public List<Admin> getAdminList() {
        return this.AdminMapper.selectAdminList();
    }

    public Admin insertAdmin(Admin admin, int regNo) {

        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        admin.setPwd(passwordEncoder.encode(admin.getId() + currentDate));
        admin.setRegNo(regNo);
        admin.setModNo(regNo);

//        Insert into Admin Table
        AdminMapper.insertAdmin(admin);
//        Insert into Roles Table
        AdminMapper.insertAdmRoles(admin);

        return admin;
    }
}