package maraisaferreira.com.github.RetroGamePlatform.security.service;

import lombok.RequiredArgsConstructor;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomNotFoundException;
import maraisaferreira.com.github.RetroGamePlatform.constants.messages.ExceptionMessages;
import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import maraisaferreira.com.github.RetroGamePlatform.repositories.PlayerRepository;
import maraisaferreira.com.github.RetroGamePlatform.security.details.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Player player = playerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ExceptionMessages.EMAIL_NOT_FOUND));
        return new User(player);
    }
}
