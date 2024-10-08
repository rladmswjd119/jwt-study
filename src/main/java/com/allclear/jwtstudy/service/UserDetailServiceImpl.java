package com.allclear.jwtstudy.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.allclear.jwtstudy.dto.UserDetailsImpl;
import com.allclear.jwtstudy.entity.User;
import com.allclear.jwtstudy.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * username에 해당하는 사용자를 조회하고 UserDetailsImpl 객체를 생성합니다.
     * @param username
     * @return UserDetails userDetailsImpl
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.of(userRepository.findByUsername(username))
                .orElseThrow(() -> new RuntimeException("가입되지 않은 계정입니다."));

        return new UserDetailsImpl(user);
    }

}
