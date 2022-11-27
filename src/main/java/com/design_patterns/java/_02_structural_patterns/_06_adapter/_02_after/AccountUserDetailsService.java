package com.design_patterns.java._02_structural_patterns._06_adapter._02_after;

import com.design_patterns.java._02_structural_patterns._06_adapter._02_after.security.UserDetails;
import com.design_patterns.java._02_structural_patterns._06_adapter._02_after.security.UserDetailsService;

public class AccountUserDetailsService implements UserDetailsService {

    AccountService accountService;

    public AccountUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUser(String username) {
        Account account = accountService.findAccountByUsername(username);
        return new AccountUserDetails(account);
    }
}
