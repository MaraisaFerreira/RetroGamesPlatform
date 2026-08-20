package maraisaferreira.com.github.RetroGamePlatform.security.component;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.security.service.TokenDataDto;
import maraisaferreira.com.github.RetroGamePlatform.security.service.TokenService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Component
public class SecurityFilterComponent extends OncePerRequestFilter {
    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = "";

        if (Strings.isNotBlank(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.split(" ")[1];
        }

        if (Strings.isNotBlank(token)) {
            TokenDataDto decoded = tokenService.validateToken(token);
            List<SimpleGrantedAuthority> authorities = switch (decoded.roles()) {
                case OWNER -> List.of(
                        new SimpleGrantedAuthority("ROLE_OWNER"),
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_BASIC")
                );
                case ADMIN -> List.of(
                        new SimpleGrantedAuthority("ROLE_ADMIN"),
                        new SimpleGrantedAuthority("ROLE_BASIC")
                );
                case BASIC -> List.of(
                        new SimpleGrantedAuthority("ROLE_BASIC")
                );
            };

            var authentication = new UsernamePasswordAuthenticationToken(decoded.email(), null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
