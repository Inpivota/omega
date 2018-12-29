package com.inpivota.omega.controller;

import com.inpivota.omega.dto.ApiResponseDto;
import com.inpivota.omega.dto.JwtAuthenticationResponseDto;
import com.inpivota.omega.dto.LoginRequestDto;
import com.inpivota.omega.dto.SignUpRequestDto;
import com.inpivota.omega.enums.RoleName;
import com.inpivota.omega.model.common.Role;
import com.inpivota.omega.model.common.User;
import com.inpivota.omega.repository.RoleRepository;
import com.inpivota.omega.repository.UserRepository;
import com.inpivota.omega.security.JwtHelper;
import com.inpivota.omega.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final int ONE_DAY_IN_SECONDS = (60 * 60 * 24);
    final AuthenticationManager authenticationManager;
    // TODO: Create user and role services
    final UserRepository userRepository;
    final RoleRepository roleRepository;

    final PasswordEncoder passwordEncoder;
    final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            final AuthenticationManager authenticationManager,
            final UserRepository userRepository,
            final RoleRepository roleRepository,
            final PasswordEncoder passwordEncoder,
            final JwtTokenProvider jwtTokenProvider) {

        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @RequestMapping(method = RequestMethod.GET)
    public boolean isAuthenticated(final HttpServletRequest request) {
        final String jwt = JwtHelper.getJwtFromRequest(request).orElse("");
        return (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt));
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> login(@Valid @RequestBody final LoginRequestDto loginRequest) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = jwtTokenProvider.generateToken(authentication);

        // TODO: Figure out how to do cookies on localhost
        //final Cookie accessTokenCookie = new Cookie("access-token", jwt);
        //accessTokenCookie.setMaxAge(ONE_DAY_IN_SECONDS);
        //response.addCookie(accessTokenCookie);
        return ResponseEntity.ok(new JwtAuthenticationResponseDto(jwt));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> register(@Valid @RequestBody final SignUpRequestDto signUpRequest) throws Exception {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponseDto(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
        }

        final User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        final Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("User role not set."));
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        final User savedUser = userRepository.save(user);

        final URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(savedUser.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponseDto(true, "User registered successfully"));
    }
}
