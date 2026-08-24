package maraisaferreira.com.github.RetroGamePlatform.security.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import maraisaferreira.com.github.RetroGamePlatform.security.details.User;
import maraisaferreira.com.github.RetroGamePlatform.security.dto.request.LoginRequestDto;
import maraisaferreira.com.github.RetroGamePlatform.security.dto.response.LoginResponseDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public LoginResponseDto login(LoginRequestDto requestDto) {
        var userAndPassword = new UsernamePasswordAuthenticationToken(requestDto.email(), requestDto.password());

        var auth = authenticationManager.authenticate(userAndPassword);

        Player player = ((User) auth.getPrincipal()).player();
        String token = tokenService.generateToken(player);

        return new LoginResponseDto("Success", token);
    }
}
