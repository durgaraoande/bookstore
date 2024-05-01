package com.abdr.bookstore.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
    @GetMapping("/login")
	public String login() {
		return "login";
	}

    @GetMapping("/register")
	public String register() {
		return "registeruser";
	}

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/register-admin")
	public String registerAdmin() {
		return "registeradmin";
	}
}
