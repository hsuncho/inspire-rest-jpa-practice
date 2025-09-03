package com.lgcns.inspire_restjpa.user.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lgcns.inspire_restjpa.user.domain.dto.UserRequestDTO;
import com.lgcns.inspire_restjpa.user.domain.dto.UserResponseDTO;
import com.lgcns.inspire_restjpa.user.domain.entity.UserEntity;
import com.lgcns.inspire_restjpa.user.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v2/inspire/user")
public class UserCtrl {

    @Autowired
    private UserService userService;
    
    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@RequestBody UserRequestDTO request) {
        System.out.println(">>> user ctrl POST / signup");
        System.out.println(">>> user ctrl POST / signup param" + request);
        UserResponseDTO response = userService.signup(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    // 인증(Authentication): 누구인지 확인하는 절차
    // Bearer token - JWT 기반 인증, OAuth2
    // token(accessToken, refreshToken)
    // 응답 시 (body x, header o) : 형태 Authorization : Bearer <token>
    
    // 인가(Authorization): 권한 부여(endpoint 접근 권한)
    // 요청 시 (header 응답 시 전송한 Bearer token 유무를 체크하고 접근 권한 확인)
    @GetMapping("/signin")
    public ResponseEntity<UserResponseDTO> getMethodName(@RequestBody UserRequestDTO request) {
        System.out.println(">>> user ctrl GET / signin");
        System.out.println(">>> user ctrl GET / signin param" + request);
        UserResponseDTO response = userService.signin(request);
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
    }
    
    
}
