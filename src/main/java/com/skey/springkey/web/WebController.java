package com.skey.springkey.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequestMapping("/test")
@Slf4j
public class WebController {

    @GetMapping("/anonymous")
    public ResponseEntity<String> getAnonymous() {
        log.warn("Test Ano Warn");
        log.error("Test Ano ERROR");
        log.debug("Test Ano DEBUG");
        log.info("Test Ano");
        return ResponseEntity.ok("Hello Anonymous");
    }

    @PreAuthorize("hasAuthority('ROLE_supervisor')")
    @GetMapping("/admin")
    public ResponseEntity<String> getAdmin(Principal principal) {
        log.info("Test Admin");
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userName = (String) token.getTokenAttributes().get("name");
        String userEmail = (String) token.getTokenAttributes().get("email");
        return ResponseEntity.ok("Hello Admin \nUser Name : " + userName + "\nUser Email : " + userEmail);
    }

    @GetMapping("/user")
    public ResponseEntity<String> getUser(Principal principal) {
        log.info("Test Uer");
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userName = (String) token.getTokenAttributes().get("name");
        String userEmail = (String) token.getTokenAttributes().get("email");
        return ResponseEntity.ok("Hello User \nUser Name : " + userName + "\nUser Email : " + userEmail);
    }
}
