package maraisaferreira.com.github.RetroGamePlatform.security.details;

import lombok.Getter;
import maraisaferreira.com.github.RetroGamePlatform.model.Player;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
public class User implements UserDetails {
    private final Player player;

    public User(Player player) {
        this.player = player;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (player.getSysRole()) {
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
    }

    @Override
    public @Nullable String getPassword() {
        return player.getPassword();
    }

    @Override
    public String getUsername() {
        return player.getEmail();
    }
}
