package maraisaferreira.com.github.RetroGamePlatform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maraisaferreira.com.github.RetroGamePlatform.config.AppConstants;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.CustomBadRequestException;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ExceptionMessages;
import maraisaferreira.com.github.RetroGamePlatform.exceptions.errorMessages.ValidationMessages;

import java.time.LocalDate;
import java.time.Period;
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

    @Setter(AccessLevel.NONE)
    @Column(nullable = false)
    @Past(message = ValidationMessages.MUST_BE_PAST_DATE)
    private LocalDate birthDate;

    @Pattern(regexp = AppConstants.EMAIL_PATTERN,
            message = ValidationMessages.NOT_A_VALID_EMAIL)
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    public Player(String name, LocalDate birthDate, String email, String password) {
        this.name = name;
        this.setBirthDate(birthDate);
        this.email = email;
        this.password = password;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (Period.between(birthDate, LocalDate.now()).getYears() < 10) {
            throw new CustomBadRequestException(ExceptionMessages.UNDER_AGE);
        }

        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
}
