package com.shing100.community.module.user;

import com.shing100.community.infra.mail.EmailMessage;
import com.shing100.community.infra.mail.EmailService;
import com.shing100.community.infra.properties.AppProperties;
import com.shing100.community.module.user.domain.Authority;
import com.shing100.community.module.user.dto.UserProfileDto;
import com.shing100.community.module.user.domain.User;
import com.shing100.community.module.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final TemplateEngine templateEngine;
    private final AppProperties appProperties;

    @Transactional
    public User signup(UserProfileDto userDto) {
        if (userRepository.findOneWithAuthoritiesByEmail(userDto.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        User user = saveNewUser(userDto);
        sendSignUpConfirmEmail(user);
        return user;
    }

    // 가입되어 있지 않은 회원이면,
    // 권한 정보 만들고 저장
    private User saveNewUser(UserProfileDto userDto) {
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .authorities(Collections.singleton(authority))
                .active(true)
                .build();
        user.generateEmailCheckToken();
        return userRepository.save(user);
    }

    public void sendSignUpConfirmEmail(User user) {
        Context context = new Context();
        context.setVariable("link", "/check-email-token?token=" + user.getEmailCheckToken() + "&email=" + user.getEmail());
        context.setVariable("nickName", user.getNickname());
        context.setVariable("linkName", "이메일 인증하기");
        context.setVariable("message", "커뮤니티 서비스를 사용하려면 링크를 클릭하세요.");
        context.setVariable("host", appProperties.getHost());
        String message = templateEngine.process("mail/simple-link", context);

        EmailMessage emailMessage = EmailMessage.builder()
                .to(user.getEmail())
                .subject("회원 가입 인증")
                .message(message)
                .build();

        emailService.sendEmail(emailMessage);
    }

    // 유저,권한 정보를 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(String username) {
        return userRepository.findOneWithAuthoritiesByEmail(username);
    }

    // 현재 securityContext에 저장된 username의 정보만 가져오는 메소드
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(userRepository::findOneWithAuthoritiesByEmail);
    }

    public boolean completeSignUp(User user, String token) {
        if (user.getEmailCheckToken().equals(token)) {
            user.completeSignUp();
            return true;
        }
        return false;
    }
}
