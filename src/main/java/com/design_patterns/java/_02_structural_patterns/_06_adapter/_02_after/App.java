package com.design_patterns.java._02_structural_patterns._06_adapter._02_after;

import com.design_patterns.java._02_structural_patterns._06_adapter._02_after.security.LoginHandler;
import com.design_patterns.java._02_structural_patterns._06_adapter._02_after.security.UserDetailsService;

public class App {

    public static void main(String[] args) {
        AccountService accountService = new AccountService();
        UserDetailsService userDetailsService = new AccountUserDetailsService(accountService);
        LoginHandler loginHandler = new LoginHandler(userDetailsService);

        String login = loginHandler.login("honggyu", "honggyu");
        System.out.println(login);

    }
}
