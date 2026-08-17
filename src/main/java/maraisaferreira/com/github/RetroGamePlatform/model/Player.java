package maraisaferreira.com.github.RetroGamePlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maraisaferreira.com.github.RetroGamePlatform.config.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ValidationMessages;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Pattern(regexp = AppConstants.EMAIL_PATTERN,
            message = ValidationMessages.NOT_A_VALID_EMAIL)
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Max(value = 100, message = ValidationMessages.MAX_VALUE_ALLOWED + "100")
    @Min(value = 5, message = ValidationMessages.LOWER_VALUE_ALLOWED + "5")
    private Integer age;

    @Column(nullable = false)
    private String password;

    public Player(String name, String email, Integer age, String password) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.password = password;
    }
}
