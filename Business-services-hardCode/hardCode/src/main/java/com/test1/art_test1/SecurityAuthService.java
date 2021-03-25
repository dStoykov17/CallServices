package com.test1.art_test1;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class SecurityAuthService {
    private final SecurityAuthDao securityAuthDao;

    public SecurityAuthService(SecurityAuthDao securityAuthDao) {
        this.securityAuthDao = securityAuthDao;
    }

    public Authentication getAuth(String username, String password) {
        return securityAuthDao.getAuthenticated(username, password);
    }
}
