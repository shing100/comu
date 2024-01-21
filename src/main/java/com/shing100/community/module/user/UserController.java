package com.shing100.community.module.user;

import com.shing100.community.module.user.dto.UserLoginDto;
import com.shing100.community.module.user.dto.UserProfileDto;
import com.shing100.community.module.user.jwt.JwtFilter;
import com.shing100.community.module.user.jwt.JwtTokenDto;
import com.shing100.community.module.user.jwt.JwtTokenProvider;
import com.shing100.community.module.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtTokenDto> authorize(@Valid @RequestBody UserLoginDto loginDto) {

        return getJwtTokenDtoResponseEntity(loginDto.getEmail(), loginDto.getPassword());
    }

    // 사용자 등록
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@Valid @RequestBody UserProfileDto userDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/check-email-token")
    public ResponseEntity<?> checkEmailToken(@Param("token") String token, @Param("email") String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (userService.completeSignUp(user, token)) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<JwtTokenDto> getJwtTokenDtoResponseEntity(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JwtTokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities().get());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username).get());
    }

    // 사용자 프로필 조회
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserProfileDto> getUserProfile(@PathVariable String userId) {
        return null;
    }

    // 사용자 프로필 업데이트
    @PostMapping("/profile/{userId}")
    public ResponseEntity<?> updateUserProfile(@PathVariable String userId, UserProfileDto updateDto) {
        return null;
    }

}
