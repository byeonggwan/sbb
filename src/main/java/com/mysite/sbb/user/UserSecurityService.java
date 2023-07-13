package com.mysite.sbb.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService { // UserDetailsService 사용
// 위 상속되는 클래스는 아래 loadUserByUsername 메소드를 강제로 Override하게 하며
// 이 메소드는 사용자명으로 비밀번호를 조회해서 리턴해야 한다.
	
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username으로 user 객체를 가져옴
    	Optional<SiteUser> _siteUser = this.userRepository.findByusername(username);
        
    	// 못 찾았을 시
        if (_siteUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
        }
        SiteUser siteUser = _siteUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        // 유저 이름에 따른 권한 부여
        if ("admin".equals(username)) {
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
        }
        
        // UserDatilsService에 상속된 클래스는 이런 식으로 DB내의 이름,패스워드, 권한을 반환해
        // Login form에서 받은 이름,패스워드와 일치하는지 확인한다
        return new User(siteUser.getUsername(), siteUser.getPassword(), authorities);
    }
}