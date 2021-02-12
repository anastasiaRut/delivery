package com.egor.delivery.controller;

import com.egor.delivery.dto.request.UserRegistrationRequestDto;
import com.egor.delivery.dto.response.UserResponseDto;
import com.egor.delivery.model.Role;
import com.egor.delivery.model.User;
import com.egor.delivery.security.TokenResponseDto;
import com.egor.delivery.security.TokenService;
import com.egor.delivery.service.RoleService;
import com.egor.delivery.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private final UserService userService;
    private final RoleService roleService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder encoder;

    /**
     * Sign in
     *
     * @param username
     * @param password
     * @return TokenResponseDto
     */
    @GetMapping("/signIn")
    public TokenResponseDto authenticateUser(@RequestParam String username, @RequestParam String password) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = userService.findByUsername(username);
        return new TokenResponseDto(tokenService.generate(authentication), user.getRole().getName(), user.getId());
    }

    /**
     * Refresh token
     *
     * @param token
     * @return
     */
    @PostMapping("/refresh")
    public TokenResponseDto refreshToken(@RequestParam String token) {
        return new TokenResponseDto(tokenService.refresh(token), "", Long.MIN_VALUE);
    }

    /**
     * Sign up
     *
     * @param userRegistrationRequestDto
     * @return User
     */
    @PostMapping("/signUp")
    public UserResponseDto registerUser(@RequestBody UserRegistrationRequestDto userRegistrationRequestDto) {
        final Role role = roleService.findByName(userRegistrationRequestDto.getRole());
        User user = new User();
        user.setUsername(userRegistrationRequestDto.getUsername());
        user.setPassword(encoder.encode(userRegistrationRequestDto.getPassword()));
        user.setName(userRegistrationRequestDto.getName());
        user.setSurname(userRegistrationRequestDto.getSurname());
        user.setRole(role);

        userService.save(user);
        UserResponseDto dto = new UserResponseDto();
        dto.setRoleId(user.getRole().getId());
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setName(user.getName());
        dto.setSurname(user.getSurname());
        return dto;
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
